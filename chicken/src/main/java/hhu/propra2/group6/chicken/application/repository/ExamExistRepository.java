package hhu.propra2.group6.chicken.application.repository;

import hhu.propra2.group6.chicken.domain.exam.Exam;

import java.util.List;

public interface ExamExistRepository {
    void save(Exam exam);

    Boolean isExist(Long lsfid);

    Exam findByLsfid(Long lsfid);

    List<Exam> findAll();

    void deleteByLsfid(Long lsfid);
}
