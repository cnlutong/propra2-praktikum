package hhu.propra2.group6.chicken.db.student;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DBStudentRepository extends CrudRepository<StudentDTO, Long> {

    StudentDTO findByOaid(Long oaid);

    boolean existsByOaid(Long oaid);

    @Query("DELETE FROM student WHERE oaid=:oaid")
    void deleteByOaid(@Param("oaid") long oaid);

}

