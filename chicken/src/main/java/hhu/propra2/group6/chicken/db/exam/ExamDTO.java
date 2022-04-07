package hhu.propra2.group6.chicken.db.exam;

import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Table("exam")
public class ExamDTO {

    @Id
    private Long id;

    private String name;
    private LocalDateTime begin;
    private LocalDateTime end;
    //    private TimePeriod timePeriod;
    private boolean online;
    private long studentid;

    public ExamDTO(Long id, String name, LocalDateTime begin, LocalDateTime end, boolean online, long studentid) {
        this.id = id;
        this.name = name;
        this.begin = begin;
        this.end = end;
//        this.timePeriod = new TimePeriod(l1, l2);
        this.online = online;
        this.studentid = studentid;
    }

    public static ExamDTO create(String name, LocalDateTime l1, LocalDateTime l2, boolean online, long studentid) {
        return new ExamDTO(null, name, l1, l2, online, studentid);
    }

    Exam toExam() {
        return new Exam(id, name, new TimePeriod(begin, end), online, studentid);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public long getStudentid() {
        return studentid;
    }

    public void setStudentid(long studentid) {
        this.studentid = studentid;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "ExamDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", l1=" + begin +
                ", l2=" + end +
                ", online=" + online +
                ", studentid=" + studentid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamDTO examDTO = (ExamDTO) o;
        return online == examDTO.online && studentid == examDTO.studentid && name.equals(examDTO.name) && begin.equals(examDTO.begin) && end.equals(examDTO.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, begin, end, online, studentid);
    }
}
