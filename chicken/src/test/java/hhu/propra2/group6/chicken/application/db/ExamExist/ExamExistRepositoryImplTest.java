package hhu.propra2.group6.chicken.application.db.ExamExist;

import hhu.propra2.group6.chicken.db.ExamExist.DBExamExistRepository;
import hhu.propra2.group6.chicken.db.ExamExist.ExamExistDTO;
import hhu.propra2.group6.chicken.db.ExamExist.ExamExistRepositoryImpl;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExamExistRepositoryImplTest {

    @Test
        //.............
    void save() {
        Exam exam = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);
        exam.setLsfid(12345L);

        DBExamExistRepository dbExamExistRepository = mock(DBExamExistRepository.class);
        when(dbExamExistRepository.existsByLsfid(12345L)).thenReturn(false);
        ExamExistRepositoryImpl examExistRepositoryImpl = new ExamExistRepositoryImpl(dbExamExistRepository);
        ExamExistDTO examExistDTO = examExistRepositoryImpl.toEntity(exam);
        examExistRepositoryImpl.save(exam);
        verify(dbExamExistRepository).save(examExistDTO);
    }

    @Test
    void isExist() {
        DBExamExistRepository dbExamExistRepository = mock(DBExamExistRepository.class);
        when(dbExamExistRepository.existsByLsfid(any())).thenReturn(true);
        ExamExistRepositoryImpl examExistRepositoryImpl = new ExamExistRepositoryImpl(dbExamExistRepository);
        Boolean exist = examExistRepositoryImpl.isExist(110L);
        assertThat(exist).isTrue();
    }

    @Test
    void isNotExist() {
        DBExamExistRepository dbExamExistRepository = mock(DBExamExistRepository.class);
        when(dbExamExistRepository.existsByLsfid(110L)).thenReturn(false);
        ExamExistRepositoryImpl examExistRepositoryImpl = new ExamExistRepositoryImpl(dbExamExistRepository);
        Boolean exist = examExistRepositoryImpl.isExist(110L);
        assertThat(exist).isFalse();
    }

    @Test
    void findByLsfid() {
        Exam exam = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);
        exam.setLsfid(12345L);
        ExamExistDTO examExistDTO = ExamExistDTO.create(exam.getName(),
                exam.getTimePeriod().getL1(), exam.getTimePeriod().getL2(), exam.isOnline(), exam.getLsfid());


        DBExamExistRepository dbExamExistRepository = mock(DBExamExistRepository.class);
        when(dbExamExistRepository.findByLsfid(12345L)).thenReturn(examExistDTO);
        ExamExistRepositoryImpl examExistRepositoryImpl = new ExamExistRepositoryImpl(dbExamExistRepository);
        Exam findExam = examExistRepositoryImpl.findByLsfid(12345L);
        assertThat(findExam.getLsfid()).isEqualTo(12345L);
    }

    @Test
    void findAll() {
        Exam exam1 = new Exam(100L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 3L);
        exam1.setLsfid(12345L);
        Exam exam2 = new Exam(100L, "FIFA", new TimePeriod(LocalDateTime.of(2022, 3, 25, 10, 0),
                LocalDateTime.of(2022, 3, 25, 11, 0)), false, 3L);
        exam2.setLsfid(22L);

        DBExamExistRepository dbExamExistRepository = mock(DBExamExistRepository.class);
        ExamExistRepositoryImpl examExistRepositoryImpl = new ExamExistRepositoryImpl(dbExamExistRepository);

        ExamExistDTO LOL = examExistRepositoryImpl.toEntity(exam1);
        ExamExistDTO FIFA = examExistRepositoryImpl.toEntity(exam2);
        when(dbExamExistRepository.findAll()).thenReturn(List.of(LOL, FIFA));
        List<Exam> examList = examExistRepositoryImpl.findAll();
        assertThat(examList.size()).isEqualTo(2);

    }
}