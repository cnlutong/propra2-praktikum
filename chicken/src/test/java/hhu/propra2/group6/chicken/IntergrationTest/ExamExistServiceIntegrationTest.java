//package hhu.propra2.group6.chicken.IntergrationTest;
//
//import hhu.propra2.group6.chicken.application.service.ExamExistService;
//import hhu.propra2.group6.chicken.domain.exam.Exam;
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
//
//
//
//@SpringBootTest
//class ExamExistServiceIntegrationTest {
//    @Autowired
//    ExamExistService examExistService;
//
//
//    Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
//            LocalDateTime.of(2022, 3, 24, 11, 0)), false, 333L);
//
//
//    @AfterEach
//    void cleanup(){
//        examExistService.deleteByLsfid(exam.getLsfid());
//    }
//
//    @Test
//    void save() {
//        exam.setLsfid(789L);
//        examExistService.save(exam);
//        assertThat(examExistService.isExist(789L)).isTrue();
//
//    }
////
////
//    @Test
//    void findByLsfid() {
//
//        exam.setLsfid(123L);
//        examExistService.save(exam);
//        assertThat(examExistService.findByLsfid(123L)).isEqualTo(exam);
//    }
//
//    @Test
//    void findAll() {
//        exam.setLsfid(456L);
//        examExistService.save(exam);
//        List<Exam> allExam = examExistService.findAll();
//
//        assertThat(allExam.contains(exam)).isTrue();
//
//    }
//}