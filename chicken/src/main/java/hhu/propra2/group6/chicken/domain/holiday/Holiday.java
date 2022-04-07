package hhu.propra2.group6.chicken.domain.holiday;

import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;

public class Holiday {

    private Long id;
    private Long studnetid;
    private TimePeriod timePeriod;

    public Holiday(Long id, TimePeriod timePeriod, Long studnetid) {
        this.id = id;
        this.timePeriod = timePeriod;
        this.studnetid = studnetid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Long getStudnetid() {
        return studnetid;
    }

    public void setStudnetid(Long studnetid) {
        this.studnetid = studnetid;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "studnetid=" + studnetid +
                ", timePeriod=" + timePeriod +
                '}';
    }

    public boolean isDeletable() {
        return this.getTimePeriod().getL2().isAfter(new CurrentTime().now());
    }
}
