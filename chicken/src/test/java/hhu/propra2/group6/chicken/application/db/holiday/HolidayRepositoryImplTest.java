package hhu.propra2.group6.chicken.application.db.holiday;

import hhu.propra2.group6.chicken.db.holiday.DBHolidayRepository;
import hhu.propra2.group6.chicken.db.holiday.HolidayDTO;
import hhu.propra2.group6.chicken.db.holiday.HolidayRepositoryImpl;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.student.Student;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class HolidayRepositoryImplTest {

    @Test
    void save() {
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0),
                LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday1 = new Holiday(1L, timePeriod1, 3L);

        DBHolidayRepository dbHolidayRepository = mock(DBHolidayRepository.class);
        HolidayRepositoryImpl holidayRepositoryImpl = new HolidayRepositoryImpl(dbHolidayRepository);
        HolidayDTO holidayDTO = holidayRepositoryImpl.toEntity(holiday1);
        holidayRepositoryImpl.save(holiday1);
        verify(dbHolidayRepository).save(holidayDTO);
    }

    @Test
    void delete() {
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0),
                LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday1 = new Holiday(1L, timePeriod1, 3L);

        DBHolidayRepository dbHolidayRepository = mock(DBHolidayRepository.class);
        HolidayRepositoryImpl holidayRepositoryImpl = new HolidayRepositoryImpl(dbHolidayRepository);

        holidayRepositoryImpl.delete(holiday1);
        verify(dbHolidayRepository).deleteByInfo(holiday1.getTimePeriod().getL1(), holiday1.getTimePeriod().getL2(), holiday1.getStudnetid());
    }

    @Test
    void clear() {
        DBHolidayRepository dbHolidayRepository = mock(DBHolidayRepository.class);
        HolidayRepositoryImpl holidayRepositoryImpl = new HolidayRepositoryImpl(dbHolidayRepository);
        holidayRepositoryImpl.clear();
        verify(dbHolidayRepository).deleteAll();

    }

    @Test
    void deleteAllByStudentid() {
        DBHolidayRepository dbHolidayRepository = mock(DBHolidayRepository.class);
        HolidayRepositoryImpl holidayRepositoryImpl = new HolidayRepositoryImpl(dbHolidayRepository);

        holidayRepositoryImpl.deleteAllByStudentid(111L);
        verify(dbHolidayRepository).deleteAllByStudentid(111L);
    }

    @Test
    void saveAllCacheToDB() {
        Student student = new Student(3L, 666L, "tong");
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0),
                LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday1 = new Holiday(1L, timePeriod1, 3L);
        TimePeriod timePeriod2 = new TimePeriod(LocalDateTime.of(2022, 3, 25, 13, 0),
                LocalDateTime.of(2022, 3, 25, 13, 30));
        Holiday holiday2 = new Holiday(2L, timePeriod2, 3L);
        student.setMyHoliday(List.of(holiday1, holiday2));

        DBHolidayRepository dbHolidayRepository = mock(DBHolidayRepository.class);
        HolidayRepositoryImpl holidayRepositoryImpl = new HolidayRepositoryImpl(dbHolidayRepository);
        HolidayDTO h1 = holidayRepositoryImpl.toEntity(holiday1);
        HolidayDTO h2 = holidayRepositoryImpl.toEntity(holiday2);


        holidayRepositoryImpl.saveAllCacheToDB(student);
        verify(dbHolidayRepository).save(h1);
        verify(dbHolidayRepository).save(h2);
    }

    @Test
    void findHolidayByID() {
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0),
                LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday1 = new Holiday(1L, timePeriod1, 3L);

        DBHolidayRepository dbHolidayRepository = mock(DBHolidayRepository.class);
        HolidayRepositoryImpl holidayRepositoryImpl = new HolidayRepositoryImpl(dbHolidayRepository);
        HolidayDTO h1 = holidayRepositoryImpl.toEntity(holiday1);
        when(dbHolidayRepository.findById(1L)).thenReturn(Optional.ofNullable(h1));

        Holiday holidayByID = holidayRepositoryImpl.findHolidayByID(1L);
        assertThat(holidayByID.getStudnetid()).isEqualTo(3);

    }

    @Test
    void findAllByStudentID() {
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0),
                LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday1 = new Holiday(1L, timePeriod1, 3L);
        TimePeriod timePeriod2 = new TimePeriod(LocalDateTime.of(2022, 3, 25, 13, 0),
                LocalDateTime.of(2022, 3, 25, 13, 30));
        Holiday holiday2 = new Holiday(2L, timePeriod2, 3L);

        DBHolidayRepository dbHolidayRepository = mock(DBHolidayRepository.class);
        HolidayRepositoryImpl holidayRepositoryImpl = new HolidayRepositoryImpl(dbHolidayRepository);

        HolidayDTO holidayDTO1 = holidayRepositoryImpl.toEntity(holiday1);
        HolidayDTO holidayDTO2 = holidayRepositoryImpl.toEntity(holiday2);
        when(dbHolidayRepository.findAllByStudentid(3L)).thenReturn(List.of(holidayDTO1, holidayDTO2));

        List<Holiday> allHolidaysByStudentID = holidayRepositoryImpl.findAllByStudentID(3L);
        assertThat(allHolidaysByStudentID.size()).isEqualTo(2);
    }
}