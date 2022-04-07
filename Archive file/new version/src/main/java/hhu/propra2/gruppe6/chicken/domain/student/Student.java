package hhu.propra2.gruppe6.chicken.domain.student;

import hhu.propra2.gruppe6.chicken.domain.date.Block;
import hhu.propra2.gruppe6.chicken.domain.date.Day;
import hhu.propra2.gruppe6.chicken.domain.date.exam.Exam;
import hhu.propra2.gruppe6.chicken.domain.log.LogList;
import hhu.propra2.gruppe6.chicken.domain.time.CurrentTime;
import hhu.propra2.gruppe6.chicken.domain.time.Time;
import hhu.propra2.gruppe6.chicken.domain.time.Timeperiod;

import java.time.*;
import java.util.ArrayList;
import java.util.UUID;

public class Student {

    private final String name;
    private final UUID id;
    private int group;
    private ArrayList<Day> calendar;
    private int restHappy; // 剩余可支配假期
    private int sumHappy;  // 总请假时间
    private ArrayList<Timeperiod> happyList; //该学生的请假清单
    private ArrayList<Exam> exams;
    private RollCall rollCall;

    public Student(String name, UUID id, int group) {
        this.name = name;
        this.id = id;
        this.group = group;
        this.calendar = createcalendar();
        this.restHappy = 240;
        this.sumHappy = 0;
        this.happyList = new ArrayList<>();
        this.exams = new ArrayList<>();
        this.rollCall = new RollCall();
    }

    public UUID getId() {
        return id;
    }

    public int getRestHappy() {
        return restHappy;
    }

    public int getSumHappy() {
        return sumHappy;
    }

    public RollCall getRollCall() {
        return rollCall;
    }

    public String getName() {
        return name;
    }

    public int getGroup() {
        return group;
    }

    public ArrayList<Timeperiod> getHappyList() {
        return happyList;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    @Override
    public String toString() {
        return "Student{" +
               "timeperiods=" + happyList +
               '}';
    }

    public ArrayList<Day> getCalendar() {
        return calendar;
    }

    private ArrayList<Day> createcalendar() {
        int sum = 15; //一天共有15天
        ArrayList<Day> calendar = new ArrayList<>();
        for (int i = 0; i < sum; i++) {
            calendar.add(new Day());
        }
        return calendar;
    }

    public String simplySetHappy(Timeperiod timepe, CurrentTime currentTime) {

        LocalDateTime t1 = timepe.getL1();
        LocalDateTime t2 = timepe.getL2();

        if ((t1.getMinute() % 15 != 0) || (t2.getMinute() % 15 != 0)) {
            return "输入的请假时间违规, 必须为一刻钟的倍数";
        }

        if (!((t1.isAfter(LocalDateTime.of(2022, 3, 7, 9, 29)))
              && (t2.isBefore(LocalDateTime.of(2022, 3, 25, 13, 31))))) {
            return "输入的请假时间违规, 不符合PB开始或结束时间";
        }

//        if (!((t1.isAfter(LocalDateTime.now()))
//                && (t2.isAfter(LocalDateTime.now())))) {
//            return "输入的请假时间违规, 请假时间必须在当天之后";
//        }

        if (t1.getDayOfMonth() == currentTime.now().getDayOfMonth()
            && t1.getDayOfMonth() == currentTime.now().getDayOfMonth()) {
            return "输入的请假时间违规, 请假时间必须在当天之后";
        }

        if (((t1.isBefore(currentTime.now())
              && (t2.isBefore(currentTime.now()))))) {
            return "输入的请假时间违规, 请假时间必须在当天之后";
        }

        if (!((LocalTime.of(t1.getHour(), t1.getMinute()).isAfter(LocalTime.of(9, 29)))
              && (LocalTime.of(t2.getHour(), t2.getMinute()).isBefore(LocalTime.of(13, 31))))) {
            return "输入的请假时间违规, 不符合每天开始或结束时间";
        }

        if (!(Duration.between(t1, t2).toMinutes() <= 150 || Duration.between(t1, t2).toMinutes() == 240)) {
            return "输入的请假时间违规，不符合请假的长度限制";
        }

        DayOfWeek dayOfWeek = t1.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return "都周末了，你还请JB假";
        }

        if (setHoliday(new Time(t1), new Time(t2))) {
          //  LogList.simplyInsertLog(this.id, this.name, "Insert Holiday " + timepe.toString());
            LogList.simplyInsertLog(this.id, this.name, "Insert Holiday " + t1+t2 );
            rollCall.insertRollCall(t1.toLocalDate(), "Holiday");
            return "请好了，滚吧";
        }
        return "没请成，SB";
    }

    public boolean setHoliday(Time von, Time bis) {
        ArrayList<Block> fake = calendar.get(von.getNumberOfDay()).deepCopy();
//
//        System.out.println("von.getNumberOfBlock()" + von.getNumberOfBlock());
//        System.out.println("bis.getNumberOfBlock()" + bis.getNumberOfBlock());

        for (int i = von.getNumberOfBlock(); i < bis.getNumberOfBlock(); i++) {
//            System.out.println(fake.get(i).getColor());
            if (fake.get(i).getColor().equals("white")) {
                fake.get(i).setColor("red");
            }
        }

        //是否当天已经只有一条请假记录
        int sumOfHappyBlock = 0;
        for (Block block : fake) {
            if (block.getColor().equals("red")) {
                sumOfHappyBlock++;
            }
        }

        //从前往后找到第一个红块
        int index1 = 0;
        for (int i = 0; i < fake.size(); i++) {
            if (fake.get(i).getColor().equals("red")) {
                index1 = i;
                break;
            }
        }

        //从后往前找到第一个红块
        int index2 = 0;
        for (int i = fake.size() - 1; i >= 0; i--) {
            if (fake.get(i).getColor().equals("red")) {
                index2 = i;
                break;
            }
        }

        //判断是否只有一段红块
        if ((index2 - index1 + 1) == sumOfHappyBlock) {

            //检查全部红块的个数
            if (checkSumOfRedBlock(calendar, fake, von.getNumberOfDay())) {
                calendar.get(von.getNumberOfDay()).setOneday(fake); //让fake生效
                refresh();
                return true;
            }
            return false;

        } else {
            if (fake.get(0).getColor().equals("red") && fake.get(fake.size() - 1).getColor().equals("red")) {
                int index3 = 0;
                for (int i = 0; i < fake.size(); i++) {
//                    if (fake.get(i).getColor().equals("white")) {
                    if (!fake.get(i).getColor().equals("red")) {
                        index3 = i;
                        break;
                    }
                }

                int index4 = 0;
                for (int i = fake.size() - 1; i >= 0; i--) {
//                    if (fake.get(i).getColor().equals("white")) {
                    if (!fake.get(i).getColor().equals("red")) {
                        index4 = i;
                        break;
                    }
                }
//                System.out.println("3: " + index3);
//                System.out.println("4: " + index4);
//                System.out.println("(index4 - index3 + 1) >= 6 :" + (index4 - index3 + 1));
//                System.out.println("fake.size() - 1 - index4 + index3: " + (fake.size() - 1 - index4 + index3));
//                System.out.println("sumOfHappyBlock: " + sumOfHappyBlock);
                if ((index4 - index3 + 1) >= 6 && (fake.size() - 1 - index4 + index3 == sumOfHappyBlock)) {
                    if (checkSumOfRedBlock(calendar, fake, von.getNumberOfDay())) {
                        calendar.get(von.getNumberOfDay()).setOneday(fake); //让fake生效
                        refresh();
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public boolean checkSumOfRedBlock(ArrayList<Day> calendar, ArrayList<Block> fake, int fakeDay) {
        int sum = 0;
        for (int i = 0; i < calendar.size(); i++) {
            for (int j = 0; j < calendar.get(0).getOneday().size(); j++) {
                if (i == fakeDay) {
                    if (fake.get(j).getColor().equals("red")) {
                        sum++;
                    }
                } else {
                    if (calendar.get(i).getOneday().get(j).getColor().equals("red")) {
                        sum++;
                    }
                }
            }
        }

//        System.out.println("sum: " + sum);
//        System.out.println("happy: " + restHappy);

        if (this.restHappy - sum * 15 < 0) {
            return false;
        } else {
            this.restHappy = 240 - sum * 15;
            this.sumHappy = sum * 15;
            return true;
        }
    }

    public void refresh() {
        int index0 = -1; //第一个坐标
        int index1 = -1; //第二个坐标
        happyList.clear();
        for (int i = 0; i < calendar.size(); i++) {

            if (calendar.get(i).getOneday().get(0).getColor().equals("red") && calendar.get(i).getOneday().get(15).getColor().equals("red")) {
                for (int j = 0; j < calendar.get(0).getOneday().size(); j++) {
//                    if (calendar.get(i).getOneday().get(j).getColor().equals("white")) {
                    if (!calendar.get(i).getOneday().get(j).getColor().equals("red")) {
                        index0 = j;
                        break;
                    }
                }
                for (int j = calendar.get(0).getOneday().size() - 1; j >= 0; j--) {
//                    if (calendar.get(i).getOneday().get(j).getColor().equals("white")) {
                    if (!calendar.get(i).getOneday().get(j).getColor().equals("red")) {
                        index1 = j;
                        break;
                    }
                }
//                int hour0 = (int) Math.ceil((index0 * 15) / 60) + 9;
//                int minute0 = (index0 * 15) % 60;
                int hour0 = (int) Math.ceil(((index0 + 1) * 15 + 30) / 60) + 9;
                int minute0 = ((index0) * 15 + 30) % 60;
//                int hour1 = (int) Math.ceil((index1 * 15) / 60) + 9;
//                int minute1 = (index1 * 15) % 60;
                int hour1 = (int) Math.ceil(((index1 + 2) * 15 + 30) / 60) + 9;
                int minute1 = ((index1 + 1) * 15 + 30) % 60;

//                System.out.println("index0: " + index0);
//                System.out.println("index1: " + index1);
                happyList.add(new Timeperiod(LocalDateTime.of(2022, 3, i + 7, 9, 30),
                        LocalDateTime.of(2022, 3, i + 7, hour0, minute0)));
                happyList.add(new Timeperiod(LocalDateTime.of(2022, 3, i + 7, hour1, minute1),
                        LocalDateTime.of(2022, 3, i + 7, 13, 30)));

                index0 = -1;
                index1 = -1;
            } else {
                for (int j = 0; j < calendar.get(0).getOneday().size(); j++) {
                    if (calendar.get(i).getOneday().get(j).getColor().equals("red")) {
                        index0 = j;
                        break;
                    }
                }
                for (int j = calendar.get(0).getOneday().size() - 1; j >= 0; j--) {
                    if (calendar.get(i).getOneday().get(j).getColor().equals("red")) {
                        index1 = j;
                        break;
                    }
                }
                if (index0 != -1 && index1 != -1) {
                    int hour0 = (int) Math.ceil(((index0 + 1) * 15 + 30) / 60) + 9;
                    int minute0 = ((index0) * 15 + 30) % 60;
                    int hour1 = (int) Math.ceil(((index1 + 1) * 15 + 30) / 60) + 9;
                    int minute1 = ((index1 + 1) * 15 + 30) % 60;
                    happyList.add(new Timeperiod(LocalDateTime.of(2022, 3, i + 7, hour0, minute0),
                            LocalDateTime.of(2022, 3, i + 7, hour1, minute1)));
                    index0 = -1;
                    index1 = -1;
                }
            }
        }
    }

    public boolean simplyDeleteHappy(Timeperiod timepe, CurrentTime currentTime) {
        if (timepe.getL1().isBefore(currentTime.now())) {
            return false;
        }

        Time von = new Time(timepe.getL1());
        Time bis = new Time(timepe.getL2());

        for (int i = von.getNumberOfBlock(); i < bis.getNumberOfBlock(); i++) {
            calendar.get(von.getNumberOfDay()).getOneday().get(i).setColor("white");
        }
        rollCall.insertRollCall(timepe.getL1().toLocalDate(), "null");
        LogList.simplyInsertLog(this.id, this.name, "Delete Holiday " + timepe.toString());
        for (int i = 0; i < happyList.size(); i++) {
            if (happyList.get(i).equals(timepe)) {
                happyList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean simplySetNotHappy(Exam exam, CurrentTime currentTime) {
        if (exam.getTimepe().getL1().isAfter(currentTime.now())) {
            Timeperiod freeTimeperiod = exam.getFreeTimeperiod();

            Time von = new Time(freeTimeperiod.getL1());
            Time bis = new Time(freeTimeperiod.getL2());

            for (int i = von.getNumberOfBlock(); i < bis.getNumberOfBlock(); i++) {
                calendar.get(von.getNumberOfDay()).getOneday().get(i).setColor("green");
            }
            exams.add(exam);
            rollCall.insertRollCall(freeTimeperiod.getL1().toLocalDate(), "Exam");
            LogList.simplyInsertLog(this.id, this.name, "Insert Exam " + exam.toString() + " " + exam.getTimepe().toString());
            checkSumOfRedBlock(calendar, null, -1);
            refresh();
            return true;
        }
        return false;
    }

    public boolean simplyDeleteNotHappy(Exam exam) {
        Timeperiod freeTimeperiod = exam.getFreeTimeperiod();

        Time von = new Time(freeTimeperiod.getL1());
        Time bis = new Time(freeTimeperiod.getL2());

        for (int i = von.getNumberOfBlock(); i < bis.getNumberOfBlock(); i++) {
            calendar.get(von.getNumberOfDay()).getOneday().get(i).setColor("white");
        }
        if (exams.remove(exam)) {
            rollCall.insertRollCall(freeTimeperiod.getL1().toLocalDate(), "null");
            LogList.simplyInsertLog(this.id, this.name, "Delete Exam " + exam.toString() + " " + exam.getTimepe().toString());
            checkSumOfRedBlock(calendar, null, -1);
            refresh();
            return true;
        }
        return false;
    }
}
