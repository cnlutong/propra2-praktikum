package hhu.propra2.group6.chicken.db.exam;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DBExamRepository extends CrudRepository<ExamDTO, Long> {
    ExamDTO findByStudentid(Long oaid);

    List<ExamDTO> findAllByStudentid(Long oaid);

    @Query("DELETE FROM exam WHERE (begin=:begin AND end=:end) AND (name=:name AND studentid=:studentid)")
    void deleteByInfo(@Param("name") String name, @Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("studentid") Long studentid);


}
