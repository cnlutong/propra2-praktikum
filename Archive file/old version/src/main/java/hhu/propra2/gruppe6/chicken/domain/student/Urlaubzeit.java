package hhu.propra2.gruppe6.chicken.domain.student;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Urlaubzeit {

    LocalDate urlaubDatum;
    LocalTime beginn;
    LocalTime end;

    @Override
    public String toString() {
        return "Urlaubzeit{" +
                "urlaubDatum=" + urlaubDatum +
                ", beginn=" + beginn +
                ", end=" + end +
                '}';
    }

    public Urlaubzeit(LocalDate urlaubDatum, LocalTime beginn, LocalTime end) {
        this.urlaubDatum = urlaubDatum;
        this.beginn = beginn;
        this.end = end;
    }

    public long urlaubDauer() {
        return Duration.between(beginn, end).toMinutes();
    }


    public boolean isErlaubt() {
        return (urlaubDauer()<= 150 || urlaubDauer()== 240) && (urlaubDatum.isAfter(LocalDate.of(2022,3,6))&& urlaubDatum.isBefore(LocalDate.of(2022,3,26)));
    }

    public boolean isStart() { //是否是当天的开始时间
        return beginn.compareTo(LocalTime.of(9,30)) == 0;

    }

    public boolean isEnd() { //是否是当天的结束时间
        return end.compareTo(LocalTime.of(13,30)) == 0;
    }

    public boolean isDauer( Urlaubzeit urlaubzeit2) { // 检查两次请假的间隔是否间隔大于90分钟
        return Duration.between(this.getEnd(),urlaubzeit2.getBeginn()).toMinutes() >= 90;
    }

    public int urlaubzeitDauer() {
        return (int)Duration.between(this.getBeginn(), this.getEnd()).toMinutes();
    }


    public boolean isSameDay(Urlaubzeit urlaubzeit){ //检查两个日期是否是同一天
        if(urlaubzeit.getUrlaubDatum().equals(this.getUrlaubDatum())){
            return true;
        }
        return false;
    }

    public LocalDate getUrlaubDatum() {
        return urlaubDatum;
    }

    public LocalTime getBeginn() {
        return beginn;
    }

    public LocalTime getEnd() {
        return end;
    }
}
