package hhu.propra2.group6.chicken.application.db.student;

import hhu.propra2.group6.chicken.db.student.DBStudentRepository;
import hhu.propra2.group6.chicken.db.student.StudentDTO;
import hhu.propra2.group6.chicken.db.student.StudentRepositoryImpl;
import hhu.propra2.group6.chicken.domain.student.Student;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StudentRepositoryImplTest {

    @Test
    void save() {
        DBStudentRepository dbStudentRepo = mock(DBStudentRepository.class);
        StudentRepositoryImpl studentRepo = new StudentRepositoryImpl(dbStudentRepo);
        Student student = new Student(3L, 1L, "tong");
        StudentDTO studentDTO = studentRepo.toEntity(student);

        studentRepo.save(student);
        verify(dbStudentRepo).save(studentDTO);
    }

    @Test
    void findStudentByID() {
        DBStudentRepository dbStudentRepo = mock(DBStudentRepository.class);
        StudentRepositoryImpl studentRepo = new StudentRepositoryImpl(dbStudentRepo);
        Student student = new Student(3L, 1L, "tong");
        StudentDTO studentDTO = studentRepo.toEntity(student);
        when(dbStudentRepo.findByOaid(1L)).thenReturn(studentDTO);

        studentRepo.findStudentByID(1L);
        verify(dbStudentRepo).findByOaid(1L);
    }

    @Test
    void clear() {
        DBStudentRepository dbStudentRepo = mock(DBStudentRepository.class);
        StudentRepositoryImpl studentRepo = new StudentRepositoryImpl(dbStudentRepo);
        studentRepo.clear();
        verify(dbStudentRepo).deleteAll();
    }

    @Test
    void isRegister() {
        DBStudentRepository dbStudentRepo = mock(DBStudentRepository.class);
        StudentRepositoryImpl studentRepo = new StudentRepositoryImpl(dbStudentRepo);
        when(dbStudentRepo.existsByOaid(3L)).thenReturn(false);

        assertThat(studentRepo.isRegister(3L)).isFalse();
    }

    @Test
    void isNotRegister() {
        DBStudentRepository dbStudentRepo = mock(DBStudentRepository.class);
        StudentRepositoryImpl studentRepo = new StudentRepositoryImpl(dbStudentRepo);
        when(dbStudentRepo.existsByOaid(3L)).thenReturn(true);

        assertThat(studentRepo.isRegister(3L)).isTrue();
    }
}