package hhu.propra2.group6.chicken.domain.holiday;

import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FormattedHolidayTest {


    @Test
    void toTimePeriod() {
        FormattedHoliday formattedHoliday = new FormattedHoliday("07.03.2022", "09:30", "10:30");
        TimePeriod timePeriod = formattedHoliday.toTimePeriod();
        assertThat(timePeriod.getL1()).isEqualTo(LocalDateTime.of(2022, 3, 7, 9, 30));
        assertThat(timePeriod.getL2()).isEqualTo(LocalDateTime.of(2022, 3, 7, 10, 30));
    }

}