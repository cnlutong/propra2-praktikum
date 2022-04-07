package hhu.propra2.group6.chicken.domain.holiday;

import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormattedHoliday {

    private String date;
    private String time1;
    private String time2;
    private String zeit;

    public FormattedHoliday(String date, String time1, String time2) {
        this.date = date;
        this.time1 = time1;
        this.time2 = time2;
        this.zeit = null;
    }

    public static FormattedHoliday FomateHolidayWithZeit(String date, String time1, String time2, String zeit) {
        FormattedHoliday formattedHoliday = new FormattedHoliday(date, time1, time2);
        formattedHoliday.setZeit(zeit);

        return formattedHoliday;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getZeit() {
        return zeit;
    }

    public void setZeit(String zeit) {
        this.zeit = zeit;
    }

    public TimePeriod toTimePeriod() {
        String strl1 = this.date.trim() + " " + this.time1.trim();
        String strl2 = this.date.trim() + " " + this.time2.trim();

        LocalDateTime l1 = stringToLocalDateTime(strl1);
        LocalDateTime l2 = stringToLocalDateTime(strl2);

        return new TimePeriod(l1, l2);
    }

    public LocalDateTime stringToLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public Boolean isNull() {
        if (this.date == null || this.time1 == null || this.time2 == null || this.zeit == null) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "FomateHoliday{" +
                "date='" + date + '\'' +
                ", time1='" + time1 + '\'' +
                ", time2='" + time2 + '\'' +
                ", zeit='" + zeit + '\'' +
                '}';
    }
}
