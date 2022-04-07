package hhu.propra2.group6.chicken.application.service;

//import hhu.propra2.group6.chicken.application.service.repository.ExamRepository;

import hhu.propra2.group6.chicken.application.repository.StudentRepository;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.student.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private ExamService examService;
    private HolidayService holidayService;

    public StudentService(StudentRepository studentRepository, ExamService examService, HolidayService holidayService) {
        this.studentRepository = studentRepository;
        this.examService = examService;
        this.holidayService = holidayService;
    }

    public void save(Student student, List<Exam> exams, List<Holiday> holidays) {

        studentRepository.save(student);
        student.setMyExam(exams);
        student.setMyHoliday(holidays);

    }

    public Student getStudent(Long oaid) {
        Student student = studentRepository.findStudentByID(oaid);
        List<Exam> exams = examService.findAllByStudentID(oaid);
        List<Holiday> holidays = holidayService.findAllByStudentID(oaid);
        student.setMyHoliday(holidays);
        student.setMyExam(exams);
        return student;
    }

    public boolean isRegister(Long oaid) {
        return studentRepository.isRegister(oaid);
    }

    public long getOAIDByName(String str) {
        return str.hashCode();
    }

    public void deleteStudentByStudentid(long studentid) {
        studentRepository.deleteStudentByStudentid(studentid);
    }

    public void clear() {
        studentRepository.clear();
    }

    public Student create(Long oaid, String name) {
        return new Student(null, oaid, name);
    }

    public int getStudentRedSum(Long oaid) {
        return getStudent(oaid).getsumRed();
    }
}
