package hhu.propra2.group6.chicken.db.student;

import hhu.propra2.group6.chicken.domain.student.Student;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("student")
public class StudentDTO {

    @Id
    private Long id;

    private Long oaid;
    private String name;

    public StudentDTO(Long id, Long oaid, String name) {
        this.id = id;
        this.oaid = oaid;
        this.name = name;

    }


    public static StudentDTO create(Long oaid, String name) {
        return new StudentDTO(null, oaid, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return oaid.equals(that.oaid) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oaid, name);
    }

    public Student toStudent() {
        return new Student(getId(), getOaid(), getName());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOaid() {
        return oaid;
    }

    public void setOaid(Long oaid) {
        this.oaid = oaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
