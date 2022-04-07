package hhu.propra2.group6.chicken.db.holiday;

import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Table("holiday")
public class HolidayDTO {

    @Id
    private Long id;
    private LocalDateTime begin;
    private LocalDateTime end;
    private Long studentid;

    public HolidayDTO(Long id, LocalDateTime begin, LocalDateTime end, Long studentid) {
        this.id = id;
        this.studentid = studentid;
        this.begin = begin;
        this.end = end;
    }

    public static HolidayDTO create(LocalDateTime l1, LocalDateTime l2, Long studentid) {
        return new HolidayDTO(null, l1, l2, studentid);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolidayDTO that = (HolidayDTO) o;
        return begin.equals(that.begin) && end.equals(that.end) && studentid.equals(that.studentid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end, studentid);
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Holiday toHoliday() {
        return new Holiday(id, new TimePeriod(begin, end), getStudentid());
    }
}
