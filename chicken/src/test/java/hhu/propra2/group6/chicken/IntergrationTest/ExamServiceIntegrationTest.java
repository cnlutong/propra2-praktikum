//package hhu.propra2.group6.chicken.IntergrationTest;
//
//import hhu.propra2.group6.chicken.application.service.ExamService;
//import hhu.propra2.group6.chicken.domain.exam.Exam;
//import hhu.propra2.group6.chicken.domain.student.Student;
//import hhu.propra2.group6.chicken.domain.time.CurrentTime;
//import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class ExamServiceIntegrationTest {
//    @Autowired
//    ExamService examService;
//
//    Exam exam = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
//            LocalDateTime.of(2022, 3, 24, 11, 0)), false, 123L);
//
//
//    @AfterEach
//    void cleanup(){
//        examService.delete(exam);
//    }
//
//    @Test
//    void save() {
//        exam.setStudentid(12345L);
//        examService.save(exam);
//        assertThat(examService.findAllByStudentID(12345L).contains(exam)).isTrue();
//    }
//
//    @Test
//    void findByStudentID() {
//        exam.setStudentid(12345L);
//        examService.save(exam);
//        assertThat(examService.findByStudentID(12345L)).isEqualTo(exam);
//    }
//
//    @Test
//    void findAllByStudentID() {
//        exam.setStudentid(12345L);
//        examService.save(exam);
//        assertThat(examService.findAllByStudentID(12345L).size()).isEqualTo(1);
//    }
//
//
//
//
//    @Test
//    void delete() {
//        exam.setStudentid(12345L);
//
//        examService.save(exam);
//        examService.delete(exam);
//        assertThat(examService.findAllByStudentID(12345L).size()).isEqualTo(0);
//
//    }
//
//
//    @Test
//    void insertGreen() {
//
//        Student student = new Student(12L,123L,"tong");
//        CurrentTime currentTime = mock(CurrentTime.class);
//        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 30));
//        examService.insertGreen(student, exam,currentTime);
//        assertThat(examService.findAllByStudentID(123L).size()).isEqualTo(1);
//    }
//
//    @Test
//    void deleteGreen() {
//
//        Student student = new Student(12L,123L,"tong");
//        CurrentTime currentTime = mock(CurrentTime.class);
//        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 30));
//
//        examService.deleteGreen(student, exam,currentTime);
//    }
//}
