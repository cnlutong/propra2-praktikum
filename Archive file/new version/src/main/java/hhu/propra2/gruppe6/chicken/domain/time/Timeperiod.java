package hhu.propra2.gruppe6.chicken.domain.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timeperiod {

    private LocalDateTime l1;
    private LocalDateTime l2;

    public Timeperiod(LocalDateTime l1, LocalDateTime l2) {
        this.l1 = l1;
        this.l2 = l2;
    }

    public LocalDateTime getL1() {
        return l1;
    }

    public LocalDateTime getL2() {
        return l2;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Timeperiod{" +
                "from: " + l1.format(formatter) +
                ", to: " + l2.format(formatter) +
                '}';
    }

    public boolean equals(Timeperiod timepe) {
        if (this.getL1().equals(timepe.getL1()) && this.getL2().equals(timepe.getL2())) {
            return true;
        }
        return false;
    }

    public void setL1(LocalDateTime l1) {
        this.l1 = l1;
    }

    public void setL2(LocalDateTime l2) {
        this.l2 = l2;
    }
}
