package hhu.propra2.group6.chicken.db.ExamExist;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DBExamExistRepository extends CrudRepository<ExamExistDTO, Long> {

    @Query("SELECT * FROM examalreadyexists")
    List<ExamExistDTO> findAll();

    ExamExistDTO findByLsfid(Long lsfid);

    Boolean existsByLsfid(Long lsfid);

    @Query("DELETE FROM examalreadyexists WHERE lsfid=:lsfid")
    void deleteByLsfid(@Param("lsfid") Long lsfid);
}
