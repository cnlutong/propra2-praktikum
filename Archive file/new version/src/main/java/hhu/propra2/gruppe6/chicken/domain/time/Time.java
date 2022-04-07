package hhu.propra2.gruppe6.chicken.domain.time;

import java.time.LocalDateTime;

public class Time {
    private final int day;
    private final int hour;
    private final int minute;
    private final int numberOfDay;
    private final int numberOfBlock;

    public Time(LocalDateTime localDateTime) {
        this.day = localDateTime.getDayOfMonth();
        this.hour = localDateTime.getHour();
        this.minute = localDateTime.getMinute();
        this.numberOfDay = this.day - 7;
        this.numberOfBlock = ((this.hour - 9) * 60 + this.minute - 30) / 15;
    }

    public int getNumberOfDay() {
        return numberOfDay;
    }

    public int getNumberOfBlock() {
        return numberOfBlock;
    }
}
