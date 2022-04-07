package hhu.propra2.group6.chicken.application.repository;

import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.student.Student;

import java.util.List;

public interface HolidayRepository {
    public void save(Holiday holiday);

    public Holiday findHolidayByID(Long id);

    List<Holiday> findAllByStudentID(Long studentid);

    void delete(Holiday holiday);

    void clear();

    void deleteAllByStudentid(Long oaid);

    void saveAllCacheToDB(Student student);
}
