package hhu.propra2.group6.chicken.application.service;

import hhu.propra2.group6.chicken.application.repository.ExamRepository;
import hhu.propra2.group6.chicken.application.repository.HolidayRepository;
import hhu.propra2.group6.chicken.application.repository.StudentRepository;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.student.Student;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Test
    void save() {
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);

        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));

        Holiday holiday = new Holiday(1L, timePeriod1, 3L);

        Student student = new Student(3L, 1L, "tong");

        StudentRepository studentRepo = mock(StudentRepository.class);
        ExamService examService = new ExamService(mock(ExamRepository.class),mock(HolidayService.class));


        HolidayService holidayService = new HolidayService(mock(HolidayRepository.class));
        StudentService studentService = new StudentService(studentRepo, examService, holidayService);
        studentService.save(student, List.of(exam), List.of(holiday));
        verify(studentRepo).save(student);
        assertThat(student.getMyExam()).isEqualTo(List.of(exam));
        assertThat(student.getMyHoliday()).isEqualTo(List.of(holiday));
    }

    @Test
    void getStudent() {
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);

        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));

        Holiday holiday = new Holiday(1L, timePeriod1, 3L);

        Student student = new Student(3L, 1L, "tong");

        StudentRepository studentRepo = mock(StudentRepository.class);
        when(studentRepo.findStudentByID(any())).thenReturn(student);

        ExamRepository examRepo = mock(ExamRepository.class);
        when(examRepo.findAllByStudentID(any())).thenReturn(List.of(exam));
        ExamService examService = new ExamService(examRepo,mock(HolidayService.class));

        List<Exam> examList = examService.findAllByStudentID(3L);

        HolidayRepository holidayRepo = mock(HolidayRepository.class);
        when(holidayRepo.findAllByStudentID(any())).thenReturn(List.of(holiday));
        HolidayService holidayService = new HolidayService(holidayRepo);
        List<Holiday> holidayList = holidayService.findAllByStudentID(3L);

        StudentService studentService = new StudentService(studentRepo, examService, holidayService);
        Student tong = studentService.getStudent(1L);
        assertThat(tong).isEqualTo(student);
        assertThat(tong.getMyHoliday()).isEqualTo(holidayList);
        assertThat(tong.getMyExam()).isEqualTo(examList);
    }

    @Test
    void isRegister() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        when(studentRepo.isRegister(any())).thenReturn(true);
        ExamService examService = new ExamService(mock(ExamRepository.class),mock(HolidayService.class));
        HolidayService holidayService = new HolidayService(mock(HolidayRepository.class));
        StudentService studentService = new StudentService(studentRepo, examService, holidayService);
        assertThat(studentService.isRegister(6L)).isTrue();
    }


    @Test
    void getStudentRedSum() {
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);

        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 24, 13, 0), LocalDateTime.of(2022, 3, 24, 13, 30));
        Holiday holiday = new Holiday(1L, timePeriod1, 3L);

        Student student = new Student(3L, 6666666L, "tong");

        StudentRepository studentRepo = mock(StudentRepository.class);
        when(studentRepo.findStudentByID(any())).thenReturn(student);

        ExamRepository examRepo = mock(ExamRepository.class);
        when(examRepo.findAllByStudentID(any())).thenReturn(List.of(exam));
        ExamService examService = new ExamService(examRepo,mock(HolidayService.class));

        HolidayRepository holidayRepo = mock(HolidayRepository.class);
        when(holidayRepo.findAllByStudentID(any())).thenReturn(List.of(holiday));
        HolidayService holidayService = new HolidayService(holidayRepo);

        StudentService studentService = new StudentService(studentRepo, examService, holidayService);
        Student tong = studentService.getStudent(6666666L);
        assertThat(tong.getsumRed()).isEqualTo(30);
    }

    @Test
    void clear() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        ExamService examService = new ExamService(mock(ExamRepository.class),mock(HolidayService.class));
        HolidayService holidayService = new HolidayService(mock(HolidayRepository.class));
        StudentService studentService = new StudentService(studentRepo, examService, holidayService);
        studentService.clear();
        verify(studentRepo).clear();
    }
}