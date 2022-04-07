package hhu.propra2.gruppe6.chicken.domain;

import hhu.propra2.gruppe6.chicken.domain.student.Urlaubzeit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlaubzeitTest {
    @Test
    @DisplayName("Dauer der Klausur")
    void test01() {
        LocalDate datum = LocalDate.of(2022, 3, 23);
        LocalTime beginn = LocalTime.of(9, 30);
        LocalTime end = LocalTime.of(12, 30);
        Urlaubzeit urlaubzeit = new Urlaubzeit(datum, beginn, end);
        boolean iserlaubt = urlaubzeit.isErlaubt();
        assertThat(iserlaubt).isFalse();
    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test02() {
        LocalDate datum = LocalDate.of(2022, 3, 23);
        LocalTime beginn = LocalTime.of(9, 30);
        LocalTime end = LocalTime.of(13, 30);
        Urlaubzeit urlaubzeit = new Urlaubzeit(datum, beginn, end);
        boolean iserlaubt = urlaubzeit.isErlaubt();
        assertThat(iserlaubt).isTrue();
    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test03() {
        LocalDate datum = LocalDate.of(2022, 3, 23);
        LocalTime beginn = LocalTime.of(9, 30);
        LocalTime end = LocalTime.of(12, 30);
        Urlaubzeit urlaubzeit = new Urlaubzeit(datum, beginn, end);
        boolean iserlaubt = urlaubzeit.isErlaubt();
        assertThat(iserlaubt).isTrue();
    }

    @Test
    @DisplayName("Test same Day")
    void test04() {
        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);


        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(12, 30);
        LocalTime end2 = LocalTime.of(13, 30);

        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        System.out.println(urlaubzeit1.isSameDay(urlaubzeit2));
    }


}
