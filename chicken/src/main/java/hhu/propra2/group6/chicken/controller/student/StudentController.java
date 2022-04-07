package hhu.propra2.group6.chicken.controller.student;

import hhu.propra2.group6.chicken.application.service.*;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.exam.FormattedExam;
import hhu.propra2.group6.chicken.domain.holiday.FormattedHoliday;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.student.Student;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import hhu.propra2.group6.chicken.domain.time.DefaultTime;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
@Secured("ROLE_STUDENT")
public class StudentController {

    private StudentService studentService;
    private ExamService examService;
    private HolidayService holidayService;
    private ExamExistService examExistService;
    private LogService logService;

    public StudentController(StudentService studentService, ExamService examService, HolidayService holidayService, ExamExistService examExistService, LogService logService) {
        this.studentService = studentService;
        this.examService = examService;
        this.holidayService = holidayService;
        this.examExistService = examExistService;
        this.logService = logService;
    }

    //    Index
    @GetMapping("/student")
    public String index(@ModelAttribute("handle") String handle, Model model) throws IOException {
//        如果该Github用户尚未注册在数据库，则自动为其注册如果该Github用户尚未注册在数据库，则自动为其注册
//        Wenn der Github-Benutzer nicht bereits in der Datenbank registriert ist, wird er automatisch für den Github-Benutzer registriert.
        if (!studentService.isRegister(studentService.getOAIDByName(handle))) {
            System.out.println("Der Benutzer ist noch nicht registriert, das System wird ihn automatisch in der Datenbank registrieren");
            Long studentid = studentService.getOAIDByName(handle);
            studentService.save(studentService.create(studentid, handle),
                    Collections.emptyList(), Collections.emptyList());
//            存储日志
//            log speichern
            logService.saveCreateStudent(studentid, handle, new CurrentTime());
            System.out.println("Automatisch dafür registriert");
            return "redirect:/student";
        } else {
            System.out.println("Willkommen zurück");
        }

        Long studentID = studentService.getOAIDByName(handle);
        List<Holiday> holidaysList = holidayService.findAllByStudentID(studentID);
        int redSum = studentService.getStudentRedSum(studentID);
        model.addAttribute("myholiday", holidaysList);
        model.addAttribute("summe", redSum);
        model.addAttribute("restUrlaub", DefaultTime.getMaxHolidayOfMinuten() - redSum);
        model.addAttribute("klausuren", examService.findAllByStudentID(studentID));
        return "student";
    }

    @PostMapping("/urlaubabmedlung/{urlaubid}")
    public String indexPost(@ModelAttribute("handle") String handle, @PathVariable("urlaubid") Long urlaubid, Model model) {
        Long studentID = studentService.getOAIDByName(handle);

        String holidayString = holidayService.findById(urlaubid).getTimePeriod().toString();

        String str = holidayService.deleteRed(studentService.getStudent(studentID), holidayService.findById(urlaubid), new CurrentTime());
//            存储日志
//            log speichern
        logService.saveDeleteHoliday(studentID, handle, holidayString, new CurrentTime());
        return "redirect:/student";
    }


    @PostMapping("/klausurabmedlung/{klausurid}")
    public String indexPost1(@ModelAttribute("handle") String handle, @PathVariable("klausurid") Long klausurid, Model model) throws IOException {
        Long studentID = studentService.getOAIDByName(handle);
        Student student = studentService.getStudent(studentID);
        Exam exam = examService.findById(klausurid);
        String klausurName = examService.findById(klausurid).getName();

        CurrentTime currentTime = new CurrentTime();
        examService.deleteGreen(student, exam, currentTime);


//            存储日志
//            log speichern
        logService.saveDeleteExamLog(studentID, handle, klausurName, new CurrentTime());
        return "redirect:/student";
    }

    //    urlaubanmeldung
    @GetMapping("/urlaubanmeldung")
    public String urlaubanmeldung(Model model) {
        return "urlaubanmeldung";
    }

    @PostMapping("/urlaubanmeldung")
    public String urlaubanmeldungPost(Model model,
                                      @ModelAttribute("formattedHoliday") FormattedHoliday formattedHoliday,
                                      @ModelAttribute("handle") String handle) {
        Log log = LogFactory.getLog(getClass());
        log.debug(formattedHoliday);

//        if(formattedHoliday.isNull()){
//            return "redirect:/urlaubanmeldung" ;
//        }

        Long studentID = studentService.getOAIDByName(handle);

        Holiday holiday = new Holiday(null, formattedHoliday.toTimePeriod(), studentID);

        String receipt = holidayService.insertRed(studentService.getStudent(studentID), holiday, new CurrentTime());


        if (receipt.equals("insertRed complete")) {
//            存储日志
//            log speichern
            logService.saveInsertHoliday(studentID, handle, holiday.getTimePeriod().toString(), new CurrentTime());

            return "redirect:/student";
        }

        model.addAttribute("urlaubform", formattedHoliday);
        model.addAttribute("error", "true");
        model.addAttribute("receipt", receipt);
        return "urlaubanmeldung";
    }

    //    klausuranmeldung
    @GetMapping("/klausuranmeldung")
    public String klausuranmeldungGet(Model model) {
        model.addAttribute("exams", examExistService.findAllCurrent(new CurrentTime()));
        return "klausuranmeldung";
    }

    @PostMapping("/klausuranmeldung")
    public String klausuranmeldungPost(Model model, @ModelAttribute("handle") String handle, @ModelAttribute("klausur") Long klausurid) {
        Log log = LogFactory.getLog(getClass());
        log.debug(klausurid);

        Long studentID = studentService.getOAIDByName(handle);
        Exam byLsfid = examExistService.findByLsfid(klausurid);
        Student student = studentService.getStudent(studentID);
        byLsfid.setStudentid(studentID);
        Boolean aBoolean = examService.insertGreen(student, byLsfid, new CurrentTime());
        holidayService.deleteAllByStudentid(studentID);
        holidayService.saveAllCacheToDB(student);
        if (aBoolean) {
//            存储日志
//            log speichern
            logService.saveInsertExamLog(studentID, handle, byLsfid.getName(), new CurrentTime());
        }
        return "redirect:/student";
    }

    //    klausuren
    @GetMapping("/klausuren")
    public String klausurenGet(Model model) {
        model.addAttribute("error", true);
        return "klausuren";
    }

    @PostMapping("/klausuren")
    public String klausurenPost(Model model,
                                @ModelAttribute("formattedExam") FormattedExam formattedExam,
                                @ModelAttribute("handle") String handle) throws IOException {
        Log log = LogFactory.getLog(getClass());
        log.debug(formattedExam);

        if (formattedExam.isNull()) {
            return "redirect:/klausuren";
        }

        Long studentID = studentService.getOAIDByName(handle);

        boolean error = true;
        Exam exam = new Exam(null, formattedExam.getName(), formattedExam.toTimePeriod(), !formattedExam.getOffline(), studentID);
        exam.setLsfid(Long.parseLong(formattedExam.getLsfid()));
        if (examExistService.getEventNameByLsfId(String.valueOf(formattedExam.getLsfid()))) {
            examExistService.save(exam);
//            存储日志
//            log speichern
            logService.saveCreateExam(studentID, handle, exam.getName(), new CurrentTime());
        } else {
            error = false;
            String receipt = "Die LSF Veranstaltungs-ID konnte nicht verifiziert werden. Falls die ID korrekt ist, melden Sie sich bitte per Mail an propra@cs.hhu.de. Geben Sie alle Informationen an, die Sie in diesem Formular eingeben müssen.";
            model.addAttribute("receipt", receipt);
        }

        model.addAttribute("klausurform", formattedExam);
        model.addAttribute("error", error);

        if (error) {
            return "redirect:/klausurangelegt";
        } else {
            return "klausuren";
        }
    }


    @GetMapping("/klausurangelegt")
    public String klausurangelegtGet(Model model) {
        model.addAttribute("error", false);
        return "klausurangelegt";
    }


    @ModelAttribute("fomateHolidayWithZeit")
    FormattedHoliday fomateHolidayWithZeit() {
        return FormattedHoliday.FomateHolidayWithZeit("", "", "", "");
    }

    @ModelAttribute("formattedHoliday")
    FormattedHoliday fomateHoliday() {
        return new FormattedHoliday("", "", "");
    }

    @ModelAttribute("formattedExam")
    FormattedExam formattedExam() {
        return new FormattedExam("", "", false, "", "", "");
    }


    @ModelAttribute("handle")
    String handle(Principal user) {
        return user.getName();
    }

}
