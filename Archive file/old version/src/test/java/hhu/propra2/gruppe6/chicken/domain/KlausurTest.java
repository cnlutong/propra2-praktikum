package hhu.propra2.gruppe6.chicken.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;


public class KlausurTest {

    @Test
    @DisplayName("Dauer der Klausur")
    void test01() {
        Klausur info1 = new Klausur("info1", LocalDate.of(2022, 3, 7),
                LocalTime.of(9, 30), LocalTime.of(11, 30),
                true, null);
        long dauer = info1.dauer();
        assertThat(dauer).isEqualTo(120);

    }

    @Test
    @DisplayName("测试考试时间是否在实习期间")
    void test02() {
        Klausur info1 = new Klausur("info1", LocalDate.of(2022, 3, 9),
                LocalTime.of(9, 30), LocalTime.of(11, 30),
                true, null);

        Boolean inPraktikum = info1.isInPraktikum();
        assertThat(inPraktikum).isTrue();

    }

}
