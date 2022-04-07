package hhu.propra2.gruppe6.chicken.domain.date.exam;

import hhu.propra2.gruppe6.chicken.domain.time.Timeperiod;

import java.time.LocalDateTime;

public class Exam {

    private String fach;
    private Timeperiod timepe;
    private boolean isOffline;
    private int id;
    private Timeperiod freeTimeperiod;

    public Exam(String fach, Timeperiod timepe, boolean isOffline, int id) {
        this.fach = fach;
        this.timepe = timepe;
        this.isOffline = isOffline;
        this.id = id;
        this.freeTimeperiod = createfreeTimeperiod();
    }

    public Timeperiod createfreeTimeperiod() {
        Timeperiod freeTimepe = new Timeperiod(null, null);
        int minutes = 30;
        if (isOffline) {
            minutes = 120;

        }
        if (timepe.getL1().minusMinutes(minutes).isBefore(LocalDateTime.of(timepe.getL1().getYear(),
                timepe.getL1().getMonth(), timepe.getL1().getDayOfMonth(), 9, 30))) {
            freeTimepe.setL1(LocalDateTime.of(timepe.getL1().getYear(),
                    timepe.getL1().getMonth(), timepe.getL1().getDayOfMonth(), 9, 30));
        } else {
            freeTimepe.setL1(timepe.getL1().minusMinutes(minutes));
        }
        if (timepe.getL2().plusMinutes(minutes).isAfter(LocalDateTime.of(timepe.getL2().getYear(),
                timepe.getL2().getMonth(), timepe.getL2().getDayOfMonth(), 13, 30))) {
            freeTimepe.setL2(LocalDateTime.of(timepe.getL2().getYear(),
                    timepe.getL2().getMonth(), timepe.getL2().getDayOfMonth(), 13, 30));
        } else {
            freeTimepe.setL2(timepe.getL2().plusMinutes(minutes));
        }
        return freeTimepe;
    }


    public Timeperiod getTimepe() {
        return timepe;
    }

    public boolean isOffline() {
        return isOffline;
    }

    public static Exam createExam(String fach, Timeperiod timepe, boolean type, int id) {
        return new Exam(fach, timepe, type, id);
    }

    public Timeperiod getFreeTimeperiod() {
        return freeTimeperiod;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "fach='" + fach + '\'' +
                ", isOffline=" + isOffline +
                ", id=" + id +
                '}';
    }
}
