package hhu.propra2.group6.chicken.application.service;

import hhu.propra2.group6.chicken.application.repository.ExamExistRepository;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ExamExistServiceTest {

    @Test
    void save() {
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 333L);
        ExamExistRepository repo = mock(ExamExistRepository.class);
        ExamExistService examExistService = new ExamExistService(repo);
        examExistService.save(exam);
        verify(repo).save(exam);
    }

    @Test
    void isExist() {
        Exam exam = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 333L);
        ExamExistRepository repo = mock(ExamExistRepository.class);
        when(repo.isExist(100L)).thenReturn(true);
        ExamExistService examExistService = new ExamExistService(repo);
        assertThat(examExistService.isExist(100L)).isTrue();
    }

    @Test
    void findByLsfid() {
        Exam exam = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 333L);
        ExamExistRepository repo = mock(ExamExistRepository.class);
        when(repo.findByLsfid(100L)).thenReturn(exam);
        ExamExistService examExistService = new ExamExistService(repo);
        assertThat(examExistService.findByLsfid(100L)).isEqualTo(exam);
    }

    @Test
    void findAll() {
        Exam exam1 = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 333L);
        Exam exam2 = new Exam(110L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 23, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), true, 333L);
        ExamExistRepository repo = mock(ExamExistRepository.class);
        when(repo.findAll()).thenReturn(List.of(exam1, exam2));
        ExamExistService examExistService = new ExamExistService(repo);
        assertThat(examExistService.findAll()).isEqualTo(List.of(exam1, exam2));
        assertThat(examExistService.findAll().size()).isEqualTo(2);
    }

    @Test
    void lsfIdIsNotExist() throws IOException {
        ExamExistService examExistService = new ExamExistService(mock(ExamExistRepository.class));
        Boolean lsfIdExist = examExistService.getEventNameByLsfId("9999999");
        assertThat(lsfIdExist).isFalse();
    }

    @Test
    void lsfIdIsExist() throws IOException {
        ExamExistService examExistService = new ExamExistService(mock(ExamExistRepository.class));
        Boolean lsfIdExist = examExistService.getEventNameByLsfId("221642");
        assertThat(lsfIdExist).isTrue();
    }

    @Test
    public void findAllCurrente() {
        Exam exam1 = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 25, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 333L);
        Exam exam2 = new Exam(110L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 23, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), true, 333L);

        List<Exam> exams = List.of(exam1, exam2);
        ExamExistRepository repo = mock(ExamExistRepository.class);
        ExamExistService examExistService = new ExamExistService(repo);

        when(repo.findAll()).thenReturn(exams);

        CurrentTime currentTime = new CurrentTime();
        currentTime.setLocalDateTime(LocalDateTime.of(2022, 3, 24, 9, 0));
        List<Exam> allCurrente = examExistService.findAllCurrent(currentTime);

        assertThat(allCurrente.size()).isEqualTo(1);
        assertThat(allCurrente.contains(exam1)).isTrue();
    }
}