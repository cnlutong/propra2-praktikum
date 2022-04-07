package hhu.propra2.group6.chicken.application.repository;

import hhu.propra2.group6.chicken.domain.exam.Exam;

import java.util.List;

public interface ExamRepository {
    void save(Exam exam);


    Exam findByStudentID(Long studentid);

    List<Exam> findAllByStudentID(Long studentid);

    void delete(Exam exam);

    void clear();

    Exam findById(Long id);
}
