//package hhu.propra2.group6.chicken.IntergrationTest;
//
//import hhu.propra2.group6.chicken.application.repository.ExamRepository;
//import hhu.propra2.group6.chicken.application.repository.HolidayRepository;
//import hhu.propra2.group6.chicken.application.repository.StudentRepository;
//import hhu.propra2.group6.chicken.application.service.ExamService;
//import hhu.propra2.group6.chicken.application.service.HolidayService;
//import hhu.propra2.group6.chicken.application.service.StudentService;
//import hhu.propra2.group6.chicken.domain.exam.Exam;
//import hhu.propra2.group6.chicken.domain.holiday.Holiday;
//import hhu.propra2.group6.chicken.domain.student.Student;
//import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class StudentServiceIntegrationTest {
//
//    @Autowired
//    StudentService studentService;
//
//    @Autowired
//    HolidayService holidayService;
//
//    @Autowired
//    ExamService examService;
//
//    Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
//            LocalDateTime.of(2022, 3, 24, 11, 0)), false, 333L);
//    TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 30, 13, 0), LocalDateTime.of(2022, 3, 30, 13, 30));
//    Holiday holiday = new Holiday(1L, timePeriod, 333L);
//    Student student = new Student(3L, 333L, "tong");
//
//
//
//
//
//    @AfterEach
//    void cleanup(){
//        holidayService.delete(holiday);
//        examService.delete(exam);
//        studentService.deleteStudentByStudentid(student.getOaid());
//    }
//
//    @Test
//    void save() {
//        studentService.save(student, List.of(exam), List.of(holiday));
//
//        assertThat(student.getMyExam()).isEqualTo(List.of(exam));
//        assertThat(student.getMyHoliday()).isEqualTo(List.of(holiday));
//
//    }
//
//
//    @Test
//    void getStudentRedSum() {
//        holidayService.save(holiday);
//        examService.save(exam);
//        studentService.save(student, List.of(exam), List.of(holiday));
//        int studentRedSum = studentService.getStudentRedSum(student.getOaid());
//        assertThat(studentRedSum).isEqualTo(30);
//    }
//
//}
