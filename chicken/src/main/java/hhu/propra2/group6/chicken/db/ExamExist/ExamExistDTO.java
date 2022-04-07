package hhu.propra2.group6.chicken.db.ExamExist;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Table("examalreadyexists")
public class ExamExistDTO {

    @Id
    private Long id;

    private String name;
    private LocalDateTime begin;
    private LocalDateTime end;
    private boolean online;
    private long lsfid;

    public ExamExistDTO(Long id, String name, LocalDateTime begin, LocalDateTime end, boolean online, long lsfid) {
        this.id = id;
        this.name = name;
        this.begin = begin;
        this.end = end;
        this.online = online;
        this.lsfid = lsfid;
    }

    public static ExamExistDTO create(String name, LocalDateTime l1, LocalDateTime l2, boolean online, long lsfid) {
        return new ExamExistDTO(null, name, l1, l2, online, lsfid);
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

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public long getLsfid() {
        return lsfid;
    }

    public void setLsfid(long lsfid) {
        this.lsfid = lsfid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamExistDTO that = (ExamExistDTO) o;
        return online == that.online && lsfid == that.lsfid && name.equals(that.name) && begin.equals(that.begin) && end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, begin, end, online, lsfid);
    }
}
