package hhu.propra2.group6.chicken.domain.exam;

import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormattedExam {

    private String name;
    private String lsfid;
    private Boolean offline;
    private String date;
    private String begin;
    private String end;

    public FormattedExam(String name, String lsfid, Boolean offline, String date, String begin, String end) {
        this.name = name;
        this.lsfid = lsfid;
        this.offline = offline;
        this.date = date;
        this.begin = begin;
        this.end = end;
    }

    public TimePeriod toTimePeriod() {
        String strl1 = this.date.trim() + " " + this.begin.trim();
        String strl2 = this.date.trim() + " " + this.end.trim();
        LocalDateTime l1 = stringToLocalDateTime(strl1);
        LocalDateTime l2 = stringToLocalDateTime(strl2);
        return new TimePeriod(l1, l2);
    }

    public LocalDateTime stringToLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public Boolean isNull() {
        if (this.name == null || this.lsfid == null || this.date == null || this.begin == null || this.end == null) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLsfid() {
        return lsfid;
    }

    public void setLsfid(String lsfid) {
        this.lsfid = lsfid;
    }

    public Boolean getOffline() {
        return offline;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "FormattedExam{" +
                "name='" + name + '\'' +
                ", lsfid=" + lsfid +
                ", offline=" + offline +
                ", date='" + date + '\'' +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
