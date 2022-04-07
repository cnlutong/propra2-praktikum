package hhu.propra2.group6.chicken.domain.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DefaultTime {

//    important!
//    Please check the initial value for the first run

    //    设定区块的大小，也就是说每多少分钟为一个区块。请您注意，系统允许的时间应该可以被您设置的区块的时间大小整除 (单位 分钟)
//    Set the size of the block, that is, how many minutes is a block. (unit minute)
//    Please note that the time allowed by the system should be divisible by the time size of the block you set
    private static int blockSizeOfMinuten = 15;

    //    设置实习开始的时间
//    Set the time when the internship starts
    private static LocalDateTime begin = LocalDateTime.of(2022, 3, 7, 9, 30);

    //    设置实习结束的时间
//    Set when the internship ends
    private static LocalDateTime end = LocalDateTime.of(2022, 4, 5, 13, 30);

    //    学生被允许的总请假时间长度 (单位 分钟)
//    The total length of time a student is allowed to leave (unit minute)
    private static int maxHolidayOfMinuten = 240;

    //    一天中被允许的非全天最大请假时长 (单位 分钟)
//    The maximum allowable part-day leave in a day (unit minute)
    private static int maxPartHolidayFromOneDayOfMinuten = 150;

    //    在线考试的开始和结束被允许的休息时间 (单位 分钟)
//    Allowed breaks for the start and end of online exams (unit minute)
    private static int freeTimeOfMinutenForOLExam = 30;

    //    非在线考试的开始和结束被允许的休息时间 (单位 分钟)
//    Allowable breaks at the beginning and end of non-online exams (unit minute)
    private static int freeTimeOfMinutenForNotOLExam = 120;


    public static int getSumOfDay() {
        return (int) Duration.between(begin, end).toDays() + 1;
    }

    public static int getOneDayOfQuater() {
        return getHoursFromEndMinusBegin() * 60 / blockSizeOfMinuten;
    }

    public static int getHoursFromEndMinusBegin() {
        return end.getHour() - begin.getHour();
    }


    public static LocalDate getBeginOfLocalDate() {
        return begin.toLocalDate();
    }

    public static LocalDate getEndOfLocalDate() {
        return end.toLocalDate();
    }


    public static LocalTime getBeginLocalTime() {
        return begin.toLocalTime();
    }

    public static LocalTime getEndLocalTime() {
        return end.toLocalTime();
    }

    public static LocalDateTime getBegin() {
        return begin;
    }

    public static void setBegin(LocalDateTime begin) {
        DefaultTime.begin = begin;
    }

    public static LocalDateTime getEnd() {
        return end;
    }

    public static void setEnd(LocalDateTime end) {
        DefaultTime.end = end;
    }

    public static int getMaxHolidayOfMinuten() {
        return maxHolidayOfMinuten;
    }

    public static void setMaxHolidayOfMinuten(int maxHolidayOfMinuten) {
        DefaultTime.maxHolidayOfMinuten = maxHolidayOfMinuten;
    }

    public static int getFreeTimeOfMinutenForOLExam() {
        return freeTimeOfMinutenForOLExam;
    }

    public static void setFreeTimeOfMinutenForOLExam(int freeTimeOfMinutenForOLExam) {
        DefaultTime.freeTimeOfMinutenForOLExam = freeTimeOfMinutenForOLExam;
    }

    public static int getBlockSizeOfMinuten() {
        return blockSizeOfMinuten;
    }

    public static void setBlockSizeOfMinuten(int blockSizeOfMinuten) {
        DefaultTime.blockSizeOfMinuten = blockSizeOfMinuten;
    }

    public static int getFreeTimeOfMinutenForNotOLExam() {
        return freeTimeOfMinutenForNotOLExam;
    }

    public static void setFreeTimeOfMinutenForNotOLExam(int freeTimeOfMinutenForNotOLExam) {
        DefaultTime.freeTimeOfMinutenForNotOLExam = freeTimeOfMinutenForNotOLExam;
    }

    public static int getMaxPartHolidayFromOneDayOfMinuten() {
        return maxPartHolidayFromOneDayOfMinuten;
    }

    public static void setMaxPartHolidayFromOneDayOfMinuten(int maxPartHolidayFromOneDayOfMinuten) {
        DefaultTime.maxPartHolidayFromOneDayOfMinuten = maxPartHolidayFromOneDayOfMinuten;
    }
}
