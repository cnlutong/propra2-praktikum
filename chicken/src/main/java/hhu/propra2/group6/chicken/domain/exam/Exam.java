package hhu.propra2.group6.chicken.domain.exam;

import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import hhu.propra2.group6.chicken.domain.time.DefaultTime;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;

import java.time.LocalDateTime;
import java.util.Objects;

public class Exam {

    private final Long id;
    private final String name;
    private final TimePeriod timePeriod;
    private final TimePeriod freeTimePeriod;
    private final boolean online;
    private Long studentid;
    private Long lsfid;

    public Exam(Long id, String name, TimePeriod timePeriod, boolean online, Long studentid) {
        this.id = id;
        this.name = name;
        this.timePeriod = timePeriod;
        this.freeTimePeriod = new TimePeriod(null, null);
        this.studentid = studentid;
        this.online = online;
        createfreeTimePeriod();
        this.lsfid = null;
    }

    private void createfreeTimePeriod() {

        int minutes = DefaultTime.getFreeTimeOfMinutenForOLExam();
        if (!online) {
            minutes = DefaultTime.getFreeTimeOfMinutenForNotOLExam();

        }
        if (timePeriod.getL1().minusMinutes(minutes).isBefore(LocalDateTime.of(timePeriod.getL1().getYear(),
                timePeriod.getL1().getMonth(), timePeriod.getL1().getDayOfMonth(), DefaultTime.getBeginLocalTime().getHour(), DefaultTime.getBeginLocalTime().getMinute()))) {
            freeTimePeriod.setL1(LocalDateTime.of(timePeriod.getL1().getYear(),
                    timePeriod.getL1().getMonth(), timePeriod.getL1().getDayOfMonth(), DefaultTime.getBeginLocalTime().getHour(), DefaultTime.getBeginLocalTime().getMinute()));
        } else {
            freeTimePeriod.setL1(timePeriod.getL1().minusMinutes(minutes));
        }
        if (timePeriod.getL2().plusMinutes(minutes).isAfter(LocalDateTime.of(timePeriod.getL2().getYear(),
                timePeriod.getL2().getMonth(), timePeriod.getL2().getDayOfMonth(), DefaultTime.getEndLocalTime().getHour(), DefaultTime.getEndLocalTime().getMinute()))) {
            freeTimePeriod.setL2(LocalDateTime.of(timePeriod.getL2().getYear(),
                    timePeriod.getL2().getMonth(), timePeriod.getL2().getDayOfMonth(), DefaultTime.getEndLocalTime().getHour(), DefaultTime.getEndLocalTime().getMinute()));
        } else {
            freeTimePeriod.setL2(timePeriod.getL2().plusMinutes(minutes));
        }
    }

    public TimePeriod getFreeTimePeriod() {
        return freeTimePeriod;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public boolean isOnline() {
        return online;
    }

    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
    }

    public Long getLsfid() {
        return lsfid;
    }

    public void setLsfid(Long lsfid) {
        this.lsfid = lsfid;
    }

    public boolean isDeletable() {
        return this.getTimePeriod().getL2().isAfter(new CurrentTime().now());
    }

    public String nameFormatter() {
        if (isOnline()) {
            return "Onlineklausur " + getName();
        }
        return "Präsenzklausur " + getName();
    }

    public String nameFormatterforOP() {
        return getName() + "(" + getTimePeriod().getL1().toLocalDate() + ", " + getTimePeriod().getL1().toLocalTime() + " - " + getTimePeriod().getL2().toLocalTime() + " Uhr )";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(lsfid, exam.lsfid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lsfid);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "name='" + name + '\'' +
                ", timePeriod=" + timePeriod +
                ", freeTimePeriod=" + freeTimePeriod +
                ", online=" + online +
                ", studentid=" + studentid +
                ", lsfid=" + lsfid +
                '}';
    }
}
