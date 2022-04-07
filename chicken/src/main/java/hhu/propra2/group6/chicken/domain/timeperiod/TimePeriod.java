package hhu.propra2.group6.chicken.domain.timeperiod;

import hhu.propra2.group6.chicken.domain.time.DefaultTime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimePeriod {

    private LocalDateTime l1;
    private LocalDateTime l2;

    public TimePeriod(LocalDateTime l1, LocalDateTime l2) {
        this.l1 = l1;
        this.l2 = l2;
    }

    public LocalDateTime getL1() {
        return l1;
    }

    public void setL1(LocalDateTime l1) {
        this.l1 = l1;
    }

    public LocalDateTime getL2() {
        return l2;
    }

    public void setL2(LocalDateTime l2) {
        this.l2 = l2;
    }

    public Long getPeriodOfMinute() {
        return Duration.between(l1, l2).toMinutes();
    }

    public int getPeriod() {
        return (int) Duration.between(l1, l2).toMinutes() / DefaultTime.getBlockSizeOfMinuten();
    }

    @Override
    public String toString() {
        return "TimePeriod{" +
                "l1=" + l1 +
                ", l2=" + l2 +
                '}';
    }

    public String formatTimePeriod() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = formatter.format(this.getL1().toLocalDate());

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String starttime = timeFormatter.format(this.getL1().toLocalTime());
        String endtime = timeFormatter.format(this.getL2().toLocalTime());
        return date + ", " + starttime + " - " + endtime + " Uhr";
    }

    public String formatFreeTimePeriod() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String starttime = timeFormatter.format(this.getL1().toLocalTime());
        String endtime = timeFormatter.format(this.getL2().toLocalTime());
        return starttime + " - " + endtime + " Uhr";
    }
}
