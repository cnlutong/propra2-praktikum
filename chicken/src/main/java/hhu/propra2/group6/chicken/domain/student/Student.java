package hhu.propra2.group6.chicken.domain.student;


import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.rule.OneRule;
import hhu.propra2.group6.chicken.domain.rule.RuleList;
import hhu.propra2.group6.chicken.domain.time.Block;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import hhu.propra2.group6.chicken.domain.time.DefaultTime;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private Long id;
    private Long oaid;
    //        Gesamte Urlaubszeit
    private int sumRed;
    //        Der Kalender dieses Schülers. Sein Urlaubs- und Prüfungsplan wird in Elementen von jeweils 15 Minuten gespeichert
    private ArrayList<Block> myCalendar;
    private List<Holiday> myHoliday;
    private List<Exam> myExam;


    public Student(Long id, Long oaid, String name) {
        this.name = name;
        this.id = id;
        this.oaid = oaid;
        this.myExam = new ArrayList<>();
        this.myHoliday = new ArrayList<>();
        this.myCalendar = new ArrayList<>();
        this.sumRed = 0;
        initCalendar();
        formatCalendar();
    }

    public List<Holiday> getMyHoliday() {
        return myHoliday;
    }

    public void setMyHoliday(List<Holiday> myHoliday) {
        this.myHoliday = myHoliday;
        formatCalendar();
    }

    public List<Exam> getMyExam() {
        return myExam;
    }

    public void setMyExam(List<Exam> myExam) {
        this.myExam = myExam;
        formatCalendar();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getsumRed() {
        sumRed = checkSumOfAllDayRed(myCalendar);
        return sumRed * DefaultTime.getBlockSizeOfMinuten();
    }

    public int getRestRed() {
        return DefaultTime.getMaxHolidayOfMinuten() - getsumRed();
    }

    public ArrayList<Block> getMyCalendar() {
        return myCalendar;
    }

    public Block getOneQuarterByTime(LocalDateTime localDateTime) {

        int index = 0;
        if (localDateTime.isAfter(DefaultTime.getBegin().minusMinutes(1))) {
            index = (int) ((Duration.between(DefaultTime.getBegin(), localDateTime).toDays() * DefaultTime.getOneDayOfQuater()) +
                    (Duration.between(DefaultTime.getBegin().toLocalTime(), localDateTime.toLocalTime()).toMinutes() / DefaultTime.getBlockSizeOfMinuten()));
        }
        return myCalendar.get(index);
    }

    //        Erstellen Sie einen neuen Schülerkalender, und legen Sie für alle Elemente die Standardfarbe Weiß fest.
    private void initCalendar() {
        int sumOfDay = DefaultTime.getSumOfDay();
        int sumOfOneDayQuarter = DefaultTime.getOneDayOfQuater();
        LocalDateTime temp = DefaultTime.getBegin();

        for (int i = 0; i < sumOfDay; i++) {
            for (int j = 0; j < sumOfOneDayQuarter; j++) {
                myCalendar.add(new Block(temp, "white", id));
                temp = temp.plusMinutes(DefaultTime.getBlockSizeOfMinuten());
            }
            temp = temp.plusDays(1).minusHours(DefaultTime.getHoursFromEndMinusBegin());
        }
    }

    //        Daten aus den Urlaubs- und Prüfungslisten lesen und in den Studentenkalender übernehmen
    private void formatCalendar() {
        for (Holiday holiday : myHoliday) {
            int point = 0;
            for (int i = 0; i < myCalendar.size(); i++) {
                if (myCalendar.get(i).getLocalDateTime().equals(holiday.getTimePeriod().getL1())) {
                    point = i;
                }
            }
            for (int j = 0; j < holiday.getTimePeriod().getPeriod(); j++) {
                if (myCalendar.get(point + j).getColor().equals("white")) {
                    myCalendar.get(point + j).setColor("red");
                }
            }
        }

        if (myExam.size() != 0) {

            for (Exam exam : myExam) {
                int point = 0;
                for (int i = 0; i < myCalendar.size(); i++) {
                    if (myCalendar.get(i).getLocalDateTime().equals(exam.getFreeTimePeriod().getL1())) {
                        point = i;
                    }
                }
                for (int j = 0; j < exam.getFreeTimePeriod().getPeriod(); j++) {
                    myCalendar.get(point + j).setColor("green");
                }
            }
        }
    }

    //        Urlaub einfügen
    public String insertRed(TimePeriod timePeriod, CurrentTime currentTime) {
        if (!checkTimePeriod(timePeriod, currentTime).equals("ok")) {
            return checkTimePeriod(timePeriod, currentTime);
        }
        ArrayList<Block> tempList = setTempRed(timePeriod);
        if (checkTempRed(tempList, timePeriod)) {
            this.myCalendar.clear();
            this.myCalendar.addAll(tempList);
            refreshHolidy();
            myCalendar = tempList;
            return "ok";
        }
        return "Ungültiger Urlaubsantrag";
    }

    //        Urlaub löschen
    public String deleteRed(TimePeriod timePeriod, CurrentTime currentTime) {

        if ((timePeriod.getL1().isBefore(currentTime.now()) && (timePeriod.getL2().isBefore(currentTime.now())))) {
            return "Die eingetragene Urlaubszeit ist unregelmäßig, der Urlaub muss nach dem Tag genommen werden";
        }
        int point = 0;
        for (int i = 0; i < myCalendar.size(); i++) {
            if (myCalendar.get(i).getLocalDateTime().equals(timePeriod.getL1())) {
                point = i;
            }
        }
        for (int j = 0; j < timePeriod.getPeriod(); j++) {
            if (myCalendar.get(point + j).getColor().equals("red")) {
                myCalendar.get(point + j).setColor("white");
            }
        }

        if (checkSumOfAllDayRed(myCalendar) == -1) {
            return "Error";
        }
        sumRed = checkSumOfAllDayRed(myCalendar);
        refreshHolidy();
        return "ok";
    }

    //        Eine Prüfung einfügen
    public Boolean insertGreen(Exam exam, CurrentTime currentTime) {

        if ((exam.getFreeTimePeriod().getL1().isBefore(currentTime.now()) && (exam.getFreeTimePeriod().getL2().isBefore(currentTime.now()))) || isExamExist(exam)) {
            return false;
        }

        myExam.add(exam);
        int point = 0;
        for (int i = 0; i < myCalendar.size(); i++) {
            if (myCalendar.get(i).getLocalDateTime().equals(exam.getFreeTimePeriod().getL1())) {
                point = i;
            }
        }
        for (int j = 0; j < exam.getFreeTimePeriod().getPeriod(); j++) {
            myCalendar.get(point + j).setColor("green");
        }
        refreshHolidy();
        return true;
    }

    //        Prüfen, ob die Prüfung bereits existiert
    private Boolean isExamExist(Exam exam) {
        boolean b = false;
        for (Exam thisexam : myExam) {
            if (thisexam.getTimePeriod().getL1().equals(exam.getTimePeriod().getL1())) {
                b = true;
                break;
            }
        }
        return b;
    }

    //        Eine Prüfung löschen
    public Boolean deleteGreen(Exam exam, CurrentTime currentTime) {
        if ((exam.getFreeTimePeriod().getL1().isBefore(currentTime.now()) && (exam.getFreeTimePeriod().getL2().isBefore(currentTime.now())))) {
            return false;
        }
        if (myExam.contains(exam)) {
            myExam.remove(exam);
            int point = 0;
            for (int i = 0; i < myCalendar.size(); i++) {
                if (myCalendar.get(i).getLocalDateTime().equals(exam.getFreeTimePeriod().getL1())) {
                    point = i;
                }
            }
            for (int j = 0; j < exam.getFreeTimePeriod().getPeriod(); j++) {
                if (myCalendar.get(point + j).getColor().equals("green")) {
                    myCalendar.get(point + j).setColor("white");
                }
            }
            refreshHolidy();
            return true;
        }
        return false;
    }

    //    Prüfen wir, ob der aktuell gewünschte Urlaubsplan der Norm entspricht
    public String checkTimePeriod(TimePeriod timePeriod, CurrentTime currentTime) {
        ArrayList<OneRule> myRuleList = RuleList.creatDefaultRuleList();
        for (OneRule oneRule : myRuleList) {
            String result = oneRule.check(timePeriod.getL1(), timePeriod.getL2(), currentTime);
            if (!result.equals("ok")) {
                return result;
            }
        }

        return "ok";
    }

    public ArrayList<Block> setTempRed(TimePeriod timePeriod) {
        ArrayList<Block> temp = deepCopy();
        int point = 0;
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getLocalDateTime().equals(timePeriod.getL1())) {
                point = i;
            }
        }
        for (int j = 0; j < timePeriod.getPeriod(); j++) {
            if (temp.get(point + j).getColor().equals("white")) {
                temp.get(point + j).setColor("red");
            }
        }
        return temp;
    }

    public boolean checkAfterDeleteExam(ArrayList<Block> temp, TimePeriod timePeriod) {
        int sumOfOneDayRed = getSumOfOneDayRed(temp, timePeriod);

        if (sumOfOneDayRed * DefaultTime.getBlockSizeOfMinuten() > DefaultTime.getMaxPartHolidayFromOneDayOfMinuten() &&
                sumOfOneDayRed * DefaultTime.getBlockSizeOfMinuten() != DefaultTime.getHoursFromEndMinusBegin() * 60) {
            return false;
        }
        return true;
    }

    private int getSumOfOneDayRed(ArrayList<Block> temp, TimePeriod timePeriod) {
        int index = 0;
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getLocalDateTime().getDayOfYear() == timePeriod.getL1().getDayOfYear()) {
                index = i;
                break;
            }
        }
        int sumOfOneDayRed = 0;
        for (int i = 0; i < DefaultTime.getOneDayOfQuater(); i++) {
            if (temp.get(i + index).getColor().equals("red")) {
                sumOfOneDayRed++;
            }
        }
        return sumOfOneDayRed;
    }

    public boolean checkTempRed(ArrayList<Block> temp, TimePeriod timePeriod) {
//        Gibt es bereits nur einen Urlaubssatz für den Tag
        if (checkSumOfAllDayRed(temp) == -1) {
            return false;
        }
        int index = 0;
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getLocalDateTime().getDayOfYear() == timePeriod.getL1().getDayOfYear()) {
                index = i;
                break;
            }
        }

//        找到当天红块的总个数
//        Ermitteln Sie die Gesamtzahl der roten Blöcke für den Tag
        int sumOfOneDayRed = 0;
        for (int i = 0; i < DefaultTime.getOneDayOfQuater(); i++) {
            if (temp.get(i + index).getColor().equals("red")) {
                sumOfOneDayRed++;
            }
        }
//        从前往后找到第一个红块
//        Finde den ersten roten Block von vorne nach hinten
        int index1 = 0;
        for (int i = 0; i < DefaultTime.getOneDayOfQuater(); i++) {
            if (temp.get(i + index).getColor().equals("red")) {
                index1 = i;
                break;
            }
        }

//        从后往前找到第一个红块
//        Finde den ersten roten Block von hinten nach vorne
        int index2 = 0;
        for (int i = DefaultTime.getOneDayOfQuater() - 1; i >= 0; i--) {
            if (temp.get(i + index).getColor().equals("red")) {
                index2 = i;
                break;
            }
        }
//        判断是否只有一段红块
//        Feststellen, ob es nur einen Abschnitt des roten Blocks gibt

//        if ((index2 - (DefaultTime.getOneDayOfQuater() - index1)) != sumOfOneDayRed) {
        if ((index2 - index1 + 1) == sumOfOneDayRed) {
            return true;

//            检查全部红块的个数
//            Überprüfen Sie die Anzahl aller roten Blöcke

        } else {
            if (temp.get(index).getColor().equals("red") && temp.get(index + DefaultTime.getBlockSizeOfMinuten()).getColor().equals("red")) {
                int index3 = 0;
                for (int i = 0; i < DefaultTime.getOneDayOfQuater(); i++) {
                    if (!temp.get(index + i).getColor().equals("red")) {
                        index3 = i;
                        break;
                    }
                }

                int index4 = 0;
                for (int i = DefaultTime.getOneDayOfQuater() - 1; i >= 0; i--) {
                    if (!temp.get(index + i).getColor().equals("red")) {
                        index4 = i;
                        break;
                    }
                }
                return (((index4 - index3 + 1) >= 6) || (index4 - index3 + 1 == 0)) && DefaultTime.getOneDayOfQuater() - 1 - index4 + index3 == sumOfOneDayRed;
            }

        }
        return false;

    }

    private int checkSumOfAllDayRed(ArrayList<Block> temp) {
        int sumOfAllDayRed = 0;
        for (Block block : temp) {
            if (block.getColor().equals("red")) {
                sumOfAllDayRed++;
            }
        }
        if (sumOfAllDayRed > (DefaultTime.getMaxHolidayOfMinuten() / DefaultTime.getBlockSizeOfMinuten())) {
            return -1;
        }
        return sumOfAllDayRed;
    }

    private void refreshHolidy() {

        int index0 = -1; //第一个坐标
        int index1 = -1; //第二个坐标
        myHoliday.clear();
        for (int i = 0; i < myCalendar.size(); i = i + DefaultTime.getOneDayOfQuater()) {

            int cout = 0;
            for (int j = 0; j < DefaultTime.getOneDayOfQuater(); j++) {
                if (myCalendar.get(i + j).getColor().equals("red")) {
                    cout++;
                }
            }

            if (!myCalendar.get(i).getColor().equals("white") && !myCalendar.get(i + DefaultTime.getOneDayOfQuater() - 1).getColor().equals("white") && cout != DefaultTime.getOneDayOfQuater()) {
//              开头结束两头请假
//              Ende des Anfangs und Ende der beiden Blätter

                int index00 = 0;
                boolean frist = true;
                for (int j = 0; j < DefaultTime.getOneDayOfQuater(); j++) {
                    if (myCalendar.get(i + j).getColor().equals("white")) {
                        index0 = i + j;
                        break;
                    } else if (myCalendar.get(i + j).getColor().equals("red") && frist) {
                        index00 = i + j;
                        frist = false;
                    }
                }

                int index11 = 0;
                frist = true;
                for (int j = DefaultTime.getOneDayOfQuater() - 1; j >= 0; j--) {
                    if (myCalendar.get(i + j).getColor().equals("white")) {
                        index1 = i + j;
                        break;
                    } else if (myCalendar.get(i + j).getColor().equals("red") && frist) {
                        index11 = i + j;
                        frist = false;
                    }
                }

                int day00 = (int) Math.floor(index00 / DefaultTime.getOneDayOfQuater());
                int hours00 = index00 % DefaultTime.getOneDayOfQuater();

                int day0 = (int) Math.floor(index0 / DefaultTime.getOneDayOfQuater());
                int hours0 = index0 % DefaultTime.getOneDayOfQuater();

                int day1 = (int) Math.floor(index1 / DefaultTime.getOneDayOfQuater());
                int hours1 = index1 % DefaultTime.getOneDayOfQuater() + 1;

                int day11 = (int) Math.floor(index11 / DefaultTime.getOneDayOfQuater());
                int hours11 = index11 % DefaultTime.getOneDayOfQuater() + 1;


                myHoliday.add(new Holiday(id, new TimePeriod(DefaultTime.getBegin().plusDays(day00).plusMinutes((long) hours00 * DefaultTime.getBlockSizeOfMinuten()),
                        DefaultTime.getBegin().plusDays(day0).plusMinutes((long) hours0 * DefaultTime.getBlockSizeOfMinuten())),
                        getOaid()));

                myHoliday.add(new Holiday(id, new TimePeriod(DefaultTime.getBegin().plusDays(day1).plusMinutes((long) hours1 * DefaultTime.getBlockSizeOfMinuten()),
                        DefaultTime.getBegin().plusDays(day11).plusMinutes((long) hours11 * DefaultTime.getBlockSizeOfMinuten())),
                        getOaid()));

                index0 = -1;
                index1 = -1;
            } else {
//              只有一段请假
//              Nur ein Absatz über die Beurlaubung
                for (int j = 0; j < DefaultTime.getOneDayOfQuater(); j++) {
                    if (myCalendar.get(i + j).getColor().equals("red")) {
                        index0 = i + j;
                        break;
                    }
                }
                for (int j = DefaultTime.getOneDayOfQuater() - 1; j >= 0; j--) {
                    if (myCalendar.get(i + j).getColor().equals("red")) {
                        index1 = i + j;
                        break;
                    }
                }

                if (index0 != -1 && index1 != -1) {

                    int day0 = (int) Math.floor(index0 / DefaultTime.getOneDayOfQuater());
                    int hours0 = index0 % DefaultTime.getOneDayOfQuater();

                    int day1 = (int) Math.floor(index1 / DefaultTime.getOneDayOfQuater());
                    int hours1 = index1 % DefaultTime.getOneDayOfQuater() + 1;

                    myHoliday.add(new Holiday(id, new TimePeriod(DefaultTime.getBegin().plusDays(day0).plusMinutes((long) hours0 * DefaultTime.getBlockSizeOfMinuten()), DefaultTime.getBegin().plusDays(day1).plusMinutes((long) hours1 * DefaultTime.getBlockSizeOfMinuten())), getOaid()));

                    index0 = -1;
                    index1 = -1;
                }
            }
        }
    }

    private ArrayList<Block> deepCopy() {
        ArrayList<Block> temp = new ArrayList<>();
        for (Block block : myCalendar) {
            temp.add(new Block(block.getLocalDateTime(), block.getColor(), block.getId()));
        }
        return temp;
    }


    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", id=" + id + ", oaid=" + oaid + ", sumRed=" + sumRed + ", myCalendar=" + myCalendar + ", myHoliday=" + myHoliday + ", myExam=" + myExam + '}';
    }
}
