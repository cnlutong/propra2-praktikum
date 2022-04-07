package hhu.propra2.gruppe6.chicken.applicationservice.repository;

import hhu.propra2.gruppe6.chicken.domain.date.exam.Exam;
import hhu.propra2.gruppe6.chicken.domain.student.Student;

import java.util.List;

public interface StudentRepository {
    void save(Student student);
    List<Student> allStudent();

    Student findStudent();

    Exam findExamsTakenByStudents();
}
