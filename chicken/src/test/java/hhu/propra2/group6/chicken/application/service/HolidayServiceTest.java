package hhu.propra2.group6.chicken.application.service;

import hhu.propra2.group6.chicken.application.repository.HolidayRepository;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.student.Student;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class HolidayServiceTest {

    @Test
    void save() {
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday = new Holiday(1L, timePeriod1, 3L);
        HolidayRepository holidayRepo = mock(HolidayRepository.class);
        HolidayService holidayService = new HolidayService(holidayRepo);
        holidayService.save(holiday);
        verify(holidayRepo).save(holiday);

    }

    @Test
    void findAllByStudentID() {
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday1 = new Holiday(1L, timePeriod1, 3L);
        TimePeriod timePeriod2 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 9, 30), LocalDateTime.of(2022, 3, 24, 10, 0));
        Holiday holiday2 = new Holiday(2L, timePeriod2, 3L);
        HolidayRepository holidayRepo = mock(HolidayRepository.class);
        when(holidayRepo.findAllByStudentID(3L)).thenReturn(List.of(holiday1, holiday2));
        HolidayService holidayService = new HolidayService(holidayRepo);
        List<Holiday> holidayList = holidayService.findAllByStudentID(3L);
        assertThat(holidayList.size()).isEqualTo(2);
    }

    @Test
    void insertRed() {
        HolidayRepository holidayrepo = mock(HolidayRepository.class);
        HolidayService holidayService = new HolidayService(holidayrepo);
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday = new Holiday(1L, timePeriod1, 3L);
        Student student = mock(Student.class);
        when(student.insertRed(any(), any())).thenReturn("ok");
        when(student.getOaid()).thenReturn(123L);
        CurrentTime currentTime = new CurrentTime();
        holidayService.insertRed(student, holiday, currentTime);

        verify(holidayrepo).saveAllCacheToDB(student);
        verify(holidayrepo).deleteAllByStudentid(student.getOaid());

    }

    @Test
    void deleteRed() {
        HolidayRepository holidayrepo = mock(HolidayRepository.class);
        HolidayService holidayService = new HolidayService(holidayrepo);
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday = new Holiday(1L, timePeriod1, 3L);
        Student student = mock(Student.class);
        when(student.deleteRed(any(), any())).thenReturn("ok");
        when(student.getOaid()).thenReturn(123L);
        CurrentTime currentTime = new CurrentTime();
        currentTime.setLocalDateTime(LocalDateTime.of(2022, 3, 24, 13, 0).minusDays(2));
        holidayService.deleteRed(student, holiday, currentTime);

        verify(holidayrepo).delete(holiday);
    }

    @Test
    void delete() {
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday1 = new Holiday(1L, timePeriod1, 3L);
        HolidayRepository holidayRepo = mock(HolidayRepository.class);
        HolidayService holidayService = new HolidayService(holidayRepo);
        holidayService.delete(holiday1);
        verify(holidayRepo).delete(holiday1);
    }


    @Test
    void clear() {
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday = new Holiday(1L, timePeriod1, 3L);
        HolidayRepository holidayRepo = mock(HolidayRepository.class);
        HolidayService holidayService = new HolidayService(holidayRepo);
        holidayService.save(holiday);
        holidayService.clear();
        verify(holidayRepo).clear();
    }

    @Test
    void findById() {
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday1 = new Holiday(1L, timePeriod1, 3L);
        HolidayRepository holidayRepo = mock(HolidayRepository.class);
        when(holidayRepo.findHolidayByID(1L)).thenReturn(holiday1);
        HolidayService holidayService = new HolidayService(holidayRepo);
        assertThat(holidayService.findById(1L)).isEqualTo(holiday1);
    }

    @Test
    void deleteAllByStudentid() {
        HolidayRepository holidayRepo = mock(HolidayRepository.class);
        HolidayService holidayService = new HolidayService(holidayRepo);
        holidayService.deleteAllByStudentid(1L);
        verify(holidayRepo).deleteAllByStudentid(1L);
    }

    @Test
    void saveAllCacheToDB() {
        HolidayRepository holidayRepo = mock(HolidayRepository.class);
        HolidayService holidayService = new HolidayService(holidayRepo);
        Student student = new Student(3L, 1L, "tong");
        holidayService.saveAllCacheToDB(student);
        verify(holidayRepo).saveAllCacheToDB(student);
    }
}