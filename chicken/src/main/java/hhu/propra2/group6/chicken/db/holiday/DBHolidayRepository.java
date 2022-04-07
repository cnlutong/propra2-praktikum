package hhu.propra2.group6.chicken.db.holiday;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DBHolidayRepository extends CrudRepository<HolidayDTO, Long> {
    HolidayDTO findByStudentid(Long studentid);

    List<HolidayDTO> findAllByStudentid(Long studentid);


    @Query("DELETE FROM holiday WHERE (begin=:begin AND end=:end) AND studentid=:studentid")
    void deleteByInfo(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("studentid") Long studentid);

    @Query("DELETE FROM holiday WHERE studentid=:studentid")
    void deleteAllByStudentid(@Param("studentid") Long oaid);

}
