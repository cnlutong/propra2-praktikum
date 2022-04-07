package hhu.propra2.group6.chicken.application.service;

import hhu.propra2.group6.chicken.application.repository.ExamRepository;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.student.Student;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class ExamServiceTest {

    @Test
    void save() {
        ExamRepository examrepo = mock(ExamRepository.class);
        HolidayService holidayService = mock(HolidayService.class);
        ExamService examService = new ExamService(examrepo, holidayService);
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, null);
        examService.save(exam);
        verify(examrepo).save(exam);
    }

    @Test
    void findByStudentID() {

        ExamRepository examrepo = mock(ExamRepository.class);
        HolidayService holidayService = mock(HolidayService.class);
        ExamService examService = new ExamService(examrepo, holidayService);
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 12345L);
        when(examrepo.findByStudentID(any())).thenReturn(exam);
        assertThat(examService.findByStudentID(12345L)).isEqualTo(exam);

    }

    @Test
    void findAllByStudentID() {
        ExamRepository examrepo = mock(ExamRepository.class);
        HolidayService holidayService = mock(HolidayService.class);
        ExamService examService = new ExamService(examrepo, holidayService);
        Exam exam1 = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 12345L);
        Exam exam2 = new Exam(100001L, "wangzherongyao", new TimePeriod(LocalDateTime.of(2022, 3, 25, 10, 0),
                LocalDateTime.of(2022, 3, 25, 11, 0)), false, 12345L);
        when(examrepo.findAllByStudentID(any())).thenReturn(List.of(exam1, exam2));
        assertThat(examService.findAllByStudentID(12345L).size()).isEqualTo(2);
    }

    @Test
    void findById() {
        ExamRepository examrepo = mock(ExamRepository.class);
        HolidayService holidayService = mock(HolidayService.class);
        ExamService examService = new ExamService(examrepo, holidayService);
        Exam exam1 = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 12345L);

        when(examrepo.findById(any())).thenReturn(exam1);

        assertThat(examService.findById(12345L)).isEqualTo(exam1);
    }


    @Test
    void delete() {
        ExamRepository examrepo = mock(ExamRepository.class);
        HolidayService holidayService = mock(HolidayService.class);
        ExamService examService = new ExamService(examrepo, holidayService);
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, null);
        examService.save(exam);
        examService.delete(exam);
        verify(examrepo).delete(exam);

    }

    @Test
    void clear() {
        ExamRepository examrepo = mock(ExamRepository.class);
        HolidayService holidayService = mock(HolidayService.class);
        ExamService examService = new ExamService(examrepo, holidayService);
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, null);
        examService.save(exam);
        examService.clear();
        verify(examrepo).clear();
    }

    @Test
    void insertGreen() {
        ExamRepository examrepo = mock(ExamRepository.class);
        HolidayService holidayService = mock(HolidayService.class);
        ExamService examService = new ExamService(examrepo, holidayService);
        Student student = mock(Student.class);
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 123L);

        when(student.insertGreen(any(), any())).thenReturn(true);
        CurrentTime currentTime = new CurrentTime();
        examService.insertGreen(student, exam, currentTime);
        verify(examrepo).save(exam);
    }

    @Test
    void deleteGreen() {
        ExamRepository examrepo = mock(ExamRepository.class);
        HolidayService holidayService = mock(HolidayService.class);
        ExamService examService = new ExamService(examrepo, holidayService);
        Student student = mock(Student.class);
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 123L);

        when(student.deleteGreen(any(), any())).thenReturn(true);
        CurrentTime currentTime = new CurrentTime();
        currentTime.setLocalDateTime(LocalDateTime.of(2022, 3, 24, 10, 0).minusDays(2));
        examService.deleteGreen(student, exam, currentTime);
        verify(examrepo).delete(exam);
    }
}