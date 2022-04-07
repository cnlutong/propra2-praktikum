//package hhu.propra2.group6.chicken.IntergrationTest;
//
//import hhu.propra2.group6.chicken.application.repository.HolidayRepository;
//import hhu.propra2.group6.chicken.application.service.HolidayService;
//import hhu.propra2.group6.chicken.domain.holiday.Holiday;
//import hhu.propra2.group6.chicken.domain.student.Student;
//import hhu.propra2.group6.chicken.domain.time.CurrentTime;
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
//@SpringBootTest
//public class HolidayServiceIntegrationTest {
//    @Autowired
//    HolidayService holidayService;
//    TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022,3,24,13,0), LocalDateTime.of(2022,3,24,13,30));
//    Holiday holiday = new Holiday(1L, timePeriod, 3L);
//
//
//    @AfterEach
//    void cleanup(){
//        holidayService.delete(holiday);
//    }
//
//    @Test
//    void findAllByStudentID() {
//        holidayService.save(holiday);
//        List<Holiday> holidayList = holidayService.findAllByStudentID(3L);
//        assertThat(holidayList.size()).isEqualTo(1);
//    }
//
//    @Test
//    void insertRed() {
//        Student student = new Student(12L,3L,"tong");
//        CurrentTime currentTime = mock(CurrentTime.class);
//        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 30));
//        holidayService.insertRed(student, holiday,currentTime);
//
//        List<Holiday> holidayList = holidayService.findAllByStudentID(3L);
//        assertThat(holidayList.size()).isEqualTo(1);
//
//    }
//
//    @Test
//    void deleteRed() {
//        Student student = new Student(12L,3L,"tong");
//        CurrentTime currentTime = mock(CurrentTime.class);
//        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 30));
//        holidayService.insertRed(student, holiday,currentTime);
//        holidayService.deleteRed(student,holiday,currentTime);
//        List<Holiday> holidayList = holidayService.findAllByStudentID(3L);
//        assertThat(holidayList.size()).isEqualTo(0);
//    }
//
//
//    @Test
//    void deleteAllByStudentid(){
//        Student student = new Student(12L,3L,"tong");
//        CurrentTime currentTime = mock(CurrentTime.class);
//        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 30));
//        holidayService.insertRed(student, holiday,currentTime);
//        holidayService.deleteAllByStudentid(3L);
//        List<Holiday> holidayList = holidayService.findAllByStudentID(3L);
//        assertThat(holidayList.size()).isEqualTo(0);
//
//    }
//
//}
