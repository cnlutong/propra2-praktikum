package hhu.propra2.gruppe6.chicken.domain.student;

import hhu.propra2.gruppe6.chicken.domain.time.Timeperiod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class RollCall {
    private HashMap<LocalDate, String> checkList;
//    private HashMap<LocalDate, Timeperiod> myStudyHistory;

    private double percentage;


    public RollCall() {
        createCheckList();
        this.percentage = 0;
    }

//
//    private void setMyStudyHistory(LocalDate localDate,Timeperiod timeperiod){
//         myStudyHistory.put(localDate,timeperiod);
//    }

    private void createCheckList() {
        this.checkList = new HashMap<>();
        for (int i = 0; i < 15; i++) {
            checkList.put(LocalDate.of(2022, 3, i + 7), "null");
        }
    }

    private void calculatePercentage() {
        int sum = 0;
        for (String str : checkList.values()) {
            if (str.equals("null")) {
                sum++;
            }
        }
        percentage = sum / 15;
    }

    public void insertRollCall(LocalDate localDate, String str) {
        checkList.put(localDate, str);
        calculatePercentage();
    }

    public String getRollCallPositon(LocalDate localDate) {
        return checkList.get(localDate);
    }

    public HashMap<LocalDate, String> getCheckList() {
        return checkList;
    }

    public double getPercentage() {
        return percentage;
    }
}