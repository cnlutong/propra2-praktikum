package hhu.propra2.gruppe6.chicken.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Klausur {
    public String fachs;
    public LocalDate datum;
    public LocalTime beginnTime;
    public LocalTime endTime;
    public boolean isPresent;
    public UUID id;

    public Klausur(String fachs, LocalDate datum, LocalTime beginnTime, LocalTime endTime, boolean isPresent, UUID id) {
        this.fachs = fachs;
        this.datum = datum;
        this.beginnTime = beginnTime;
        this.endTime = endTime;
        this.isPresent = isPresent;
        this.id = id;
    }

    public long dauer() {
        return Duration.between(beginnTime, endTime).toMinutes();
    }


    public Boolean isInPraktikum() {
        LocalDate pbegin = LocalDate.of(2022, 3, 7);
        LocalDate pend = LocalDate.of(2022, 3, 25);
        return datum.isAfter(pbegin) && datum.isBefore(pend);
    }

    public UUID getId() {
        return id;
    }
}
