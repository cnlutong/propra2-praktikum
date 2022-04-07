package hhu.propra2.group6.chicken.application.db.exam;

import hhu.propra2.group6.chicken.db.exam.DBExamRepository;
import hhu.propra2.group6.chicken.db.exam.ExamDTO;
import hhu.propra2.group6.chicken.db.exam.ExamRepositoryImpl;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ExamRepositoryImplTest {

    @Test
    void save() {
        Exam exam = new Exam(null, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);
        DBExamRepository dbExamRepository = mock(DBExamRepository.class);
        ExamRepositoryImpl examRepositoryImpl = new ExamRepositoryImpl(dbExamRepository);
        ExamDTO examDTO = examRepositoryImpl.toEntity(exam);
        examRepositoryImpl.save(exam);
        verify(dbExamRepository).save(examDTO);
    }

    @Test
    void delete() {
        Exam exam = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);

        DBExamRepository dbExamRepository = mock(DBExamRepository.class);
        ExamRepositoryImpl examRepositoryImpl = new ExamRepositoryImpl(dbExamRepository);
        examRepositoryImpl.delete(exam);
        verify(dbExamRepository).deleteByInfo(exam.getName(), exam.getTimePeriod().getL1(),
                exam.getTimePeriod().getL2(), exam.getStudentid());
    }

    @Test
    void clear() {
        DBExamRepository dbExamRepository = mock(DBExamRepository.class);
        ExamRepositoryImpl examRepositoryImpl = new ExamRepositoryImpl(dbExamRepository);
        examRepositoryImpl.clear();
        verify(dbExamRepository).deleteAll();
    }

    @Test
    void findById() {
        Exam exam = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);

        DBExamRepository dbExamRepository = mock(DBExamRepository.class);
        ExamRepositoryImpl examRepositoryImpl = new ExamRepositoryImpl(dbExamRepository);
        when(dbExamRepository.findById(any())).thenReturn(Optional.ofNullable(examRepositoryImpl.toEntity(exam)));
        Exam findExam = examRepositoryImpl.findById(100L);
        verify(dbExamRepository).findById(100L);
        assertThat(findExam.getName()).isEqualTo(exam.getName());
        assertThat(findExam.getStudentid()).isEqualTo(exam.getStudentid());
    }

    @Test
    void findAllByStudentID() {
        Exam exam1 = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);

        Exam exam2 = new Exam(110L, "FIFA", new TimePeriod(LocalDateTime.of(2022, 3, 25, 10, 0),
                LocalDateTime.of(2022, 3, 25, 11, 0)), true, 3L);


        DBExamRepository dbExamRepository = mock(DBExamRepository.class);
        ExamRepositoryImpl examRepositoryImpl = new ExamRepositoryImpl(dbExamRepository);

        ExamDTO examDTO1 = examRepositoryImpl.toEntity(exam1);
        ExamDTO examDTO2 = examRepositoryImpl.toEntity(exam2);
        when(dbExamRepository.findAllByStudentid(any())).thenReturn(List.of(examDTO1, examDTO2));

        List<Exam> examList = examRepositoryImpl.findAllByStudentID(3L);
        assertThat(examList.size()).isEqualTo(2);

    }

    @Test
    void toEntity() {
        ExamRepositoryImpl examRepositoryImpl = new ExamRepositoryImpl(mock(DBExamRepository.class));

        Exam exam = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);
        ExamDTO examDTO = examRepositoryImpl.toEntity(exam);
        assertThat(examDTO.getStudentid()).isEqualTo(3);
        assertThat(examDTO.isOnline()).isEqualTo(false);
    }
}