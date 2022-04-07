package hhu.propra2.group6.chicken.application.repository;

import hhu.propra2.group6.chicken.domain.student.Student;

public interface StudentRepository {

    public void save(Student student);

    public Student findStudentByID(Long id);

    void clear();

    boolean isRegister(Long oaid);

    void deleteStudentByStudentid(long studentid);
}
