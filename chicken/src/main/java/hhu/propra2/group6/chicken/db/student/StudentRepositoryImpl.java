package hhu.propra2.group6.chicken.db.student;

import hhu.propra2.group6.chicken.application.repository.StudentRepository;
import hhu.propra2.group6.chicken.domain.student.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final DBStudentRepository repo;

    public StudentRepositoryImpl(DBStudentRepository repo) {
        this.repo = repo;

    }


    @Override
    public void save(Student student) {
        repo.save(toEntity(student));
    }


    @Override
    public Student findStudentByID(Long id) {
        return toStudent(repo.findByOaid(id));

    }


    @Override
    public void clear() {
        repo.deleteAll();
    }

    @Override
    public boolean isRegister(Long oaid) {
        return repo.existsByOaid(oaid);
    }

    @Override
    public void deleteStudentByStudentid(long studentid) {
        repo.deleteByOaid(studentid);
    }

    public Student toStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getId(), studentDTO.getOaid(), studentDTO.getName());
    }

    public StudentDTO toEntity(Student student) {
        return new StudentDTO(null, student.getOaid(), student.getName());
    }


}
