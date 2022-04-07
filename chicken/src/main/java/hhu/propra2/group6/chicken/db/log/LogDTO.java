package hhu.propra2.group6.chicken.db.log;

import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Table("log")
public class LogDTO {

    @Id
    private Long id;
    private String type;
    private LocalDateTime time;
    private Long studentid;
    private String name;
    private String action;

    public LogDTO(Long id, String type, Long studentid, String name, String action, CurrentTime currentTime) {
        this.id = id;
        this.type = type;
        this.time = currentTime.now();
        this.studentid = studentid;
        this.name = name;
        this.action = action;
    }

    public static LogDTO creat(String type, Long studentid, String name, String action, CurrentTime currentTime) {
        return new LogDTO(null, type, studentid, name, action, currentTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogDTO logDTO = (LogDTO) o;
        return Objects.equals(type, logDTO.type) && Objects.equals(studentid, logDTO.studentid) && Objects.equals(name, logDTO.name) && Objects.equals(action, logDTO.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, studentid, name, action);
    }
}
