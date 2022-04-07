package hhu.propra2.group6.chicken.domain.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DefaultTimeTest {

    @Test
    void getSumOfDay() {
        DefaultTime.setBegin(LocalDateTime.of(2022, 3, 27, 9, 30));
        DefaultTime.setEnd(LocalDateTime.of(2022, 3, 28, 9, 30));

        assertThat(DefaultTime.getSumOfDay()).isEqualTo(2);
    }

    @Test
    void getOneDayOfQuater() {
        DefaultTime.setBegin(LocalDateTime.of(2022, 3, 7, 9, 30));
        DefaultTime.setEnd(LocalDateTime.of(2022, 3, 25, 13, 30));
        DefaultTime.setBlockSizeOfMinuten(30);

        assertThat(DefaultTime.getOneDayOfQuater()).isEqualTo(8);

    }

    @Test
    void getHoursFromEndMinusBegin() {
        DefaultTime.setBegin(LocalDateTime.of(2022, 3, 7, 9, 30));
        DefaultTime.setEnd(LocalDateTime.of(2022, 3, 25, 13, 30));

        assertThat(DefaultTime.getHoursFromEndMinusBegin()).isEqualTo(4);
    }
}