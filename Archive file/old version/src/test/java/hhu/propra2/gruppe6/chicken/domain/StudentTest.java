package hhu.propra2.gruppe6.chicken.domain;

import hhu.propra2.gruppe6.chicken.domain.student.Ordnung;
import hhu.propra2.gruppe6.chicken.domain.student.Student;
import hhu.propra2.gruppe6.chicken.domain.student.Urlaubzeit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {
    @Test
    @DisplayName("Dauer der Klausur")
    void test01() {

        ArrayList<Urlaubzeit> urlaubzeitArrayList = new ArrayList<>();
        Student student = new Student(null, null, urlaubzeitArrayList, null);
        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);


        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(12, 30);
        LocalTime end2 = LocalTime.of(13, 30);

        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        student.urlaubzeitAnmelden(urlaubzeit1);
        student.urlaubzeitAnmelden(urlaubzeit2);

//        urlaubzeitArrayList.stream().forEach(System.out::println);
//        assertThat(student.urlaubzeitList.size()).isEqualTo(2);

        System.out.println(urlaubzeit1.getBeginn().compareTo(urlaubzeit2.getBeginn()));
    }


    @Test
    @DisplayName("Dauer der Klausur")
    void test02() {

        ArrayList<Urlaubzeit> urlaubzeitArrayList = new ArrayList<>();
        Student student = new Student(null, null, urlaubzeitArrayList, null);
        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);


        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(12, 30);
        LocalTime end2 = LocalTime.of(13, 30);


        LocalDate datum3 = LocalDate.of(2022, 3, 9);
        LocalTime beginn3 = LocalTime.of(12, 30);
        LocalTime end3 = LocalTime.of(13, 30);


        LocalDate datum4 = LocalDate.of(2022, 3, 10);
        LocalTime beginn5 = LocalTime.of(12, 30);
        LocalTime end6 = LocalTime.of(13, 30);

        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);
        Urlaubzeit urlaubzeit3 = new Urlaubzeit(datum3, beginn3, end3);
        Urlaubzeit urlaubzeit4 = new Urlaubzeit(datum4, beginn5, end6);


//        student.urlaubzeitAnmelden(urlaubzeit1);
//        student.urlaubzeitAnmelden(urlaubzeit2);

        urlaubzeitArrayList.add(urlaubzeit1);
        urlaubzeitArrayList.add(urlaubzeit2);
        urlaubzeitArrayList.add(urlaubzeit3);
        urlaubzeitArrayList.add(urlaubzeit4);
        student.sort();
        //System.out.println(urlaubzeitArrayList);

        urlaubzeitArrayList.stream().forEach(System.out::println);
//        assertThat(student.urlaubzeitList.size()).isEqualTo(2);

        System.out.println(urlaubzeit1.getBeginn().compareTo(urlaubzeit2.getBeginn()));
    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test03() {

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(10, 0);
        LocalTime end1 = LocalTime.of(12, 30);


        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(10, 30);
        LocalTime end2 = LocalTime.of(11, 30);

        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.istEsEnthalten(urlaubzeit1, urlaubzeit2);

        assertThat(i).isEqualTo(21);

    }


    @Test
    @DisplayName("Dauer der Klausur")
    void test04() {

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(10, 0);
        LocalTime end1 = LocalTime.of(12, 30);


        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(10, 30);
        LocalTime end2 = LocalTime.of(11, 30);

        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.istEsEnthalten(urlaubzeit2, urlaubzeit1);

        assertThat(i).isEqualTo(22);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test05() {

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(10, 0);
        LocalTime end1 = LocalTime.of(11, 30);


        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(10, 30);
        LocalTime end2 = LocalTime.of(12, 30);

        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.istVerbunden(urlaubzeit1, urlaubzeit2);

        assertThat(i).isEqualTo(31);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test06() {

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(10, 0);
        LocalTime end1 = LocalTime.of(11, 30);


        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(10, 30);
        LocalTime end2 = LocalTime.of(12, 30);

        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.istVerbunden(urlaubzeit2, urlaubzeit1);

        assertThat(i).isEqualTo(32);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test07() {

        ArrayList<Urlaubzeit> urlaubzeit = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(10, 0);
        LocalTime end1 = LocalTime.of(11, 30);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);


        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit1, urlaubzeit);

        assertThat(i).isEqualTo(1);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test08() {

        ArrayList<Urlaubzeit> urlaubzeit = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(10, 0);
        LocalTime end1 = LocalTime.of(13, 30);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);


        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit1, urlaubzeit);

        assertThat(i).isEqualTo(0);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test09() {

        ArrayList<Urlaubzeit> urlaubzeit = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(10, 0);
        LocalTime end1 = LocalTime.of(13, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 22);
        LocalTime beginn2 = LocalTime.of(10, 30);
        LocalTime end2 = LocalTime.of(12, 30);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        urlaubzeit.add(urlaubzeit1);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit2, urlaubzeit);

        assertThat(i).isEqualTo(1);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test10() {

        ArrayList<Urlaubzeit> urlaubzeitList = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(10, 0);
        LocalTime end1 = LocalTime.of(10, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(10, 30);
        LocalTime end2 = LocalTime.of(12, 30);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        urlaubzeitList.add(urlaubzeit1);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit2, urlaubzeitList);

        assertThat(i).isEqualTo(31);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test11() {

        ArrayList<Urlaubzeit> urlaubzeitList = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(12, 30);
        LocalTime end2 = LocalTime.of(13, 30);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        urlaubzeitList.add(urlaubzeit1);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit2, urlaubzeitList);

        assertThat(i).isEqualTo(1);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test12() {

        ArrayList<Urlaubzeit> urlaubzeitList = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(10, 0);
        LocalTime end1 = LocalTime.of(10, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(10, 30);
        LocalTime end2 = LocalTime.of(12, 30);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        urlaubzeitList.add(urlaubzeit2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit1, urlaubzeitList);

        assertThat(i).isEqualTo(32);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test13() {

        ArrayList<Urlaubzeit> urlaubzeitList = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(12, 30);
        LocalTime end2 = LocalTime.of(13, 30);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);

        urlaubzeitList.add(urlaubzeit2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit1, urlaubzeitList);

        assertThat(i).isEqualTo(1);

    }


    @Test
    @DisplayName("Dauer der Klausur")
    void test14() {

        ArrayList<Urlaubzeit> urlaubzeitList = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(12, 30);
        LocalTime end2 = LocalTime.of(13, 30);


        LocalDate datum3 = LocalDate.of(2022, 3, 23);
        LocalTime beginn3 = LocalTime.of(10, 30);
        LocalTime end3 = LocalTime.of(11, 0);



        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);
        Urlaubzeit urlaubzeit3 = new Urlaubzeit(datum3, beginn3, end3);

        urlaubzeitList.add(urlaubzeit1);
        urlaubzeitList.add(urlaubzeit2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit3, urlaubzeitList);

        assertThat(i).isEqualTo(31);

    }



    @Test
    @DisplayName("Dauer der Klausur")
    void test15() {

        ArrayList<Urlaubzeit> urlaubzeitList = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(12, 30);
        LocalTime end2 = LocalTime.of(13, 30);


        LocalDate datum3 = LocalDate.of(2022, 3, 23);
        LocalTime beginn3 = LocalTime.of(11, 30);
        LocalTime end3 = LocalTime.of(12, 30);



        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);
        Urlaubzeit urlaubzeit3 = new Urlaubzeit(datum3, beginn3, end3);

        urlaubzeitList.add(urlaubzeit1);
        urlaubzeitList.add(urlaubzeit2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit3, urlaubzeitList);

        assertThat(i).isEqualTo(0);

    }
    @Test
    @DisplayName("Dauer der Klausur")
    void test16() {

        ArrayList<Urlaubzeit> urlaubzeitList = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(13, 0);
        LocalTime end2 = LocalTime.of(13, 30);


        LocalDate datum3 = LocalDate.of(2022, 3, 23);
        LocalTime beginn3 = LocalTime.of(11, 30);
        LocalTime end3 = LocalTime.of(12, 0);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);
        Urlaubzeit urlaubzeit3 = new Urlaubzeit(datum3, beginn3, end3);

        urlaubzeitList.add(urlaubzeit1);
        urlaubzeitList.add(urlaubzeit2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit3, urlaubzeitList);

        assertThat(i).isEqualTo(0);

    }


    @Test
    @DisplayName("Dauer der Klausur")
    void test17() {

        ArrayList<Urlaubzeit> urlaubzeitList = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(13, 0);
        LocalTime end2 = LocalTime.of(13, 30);


        LocalDate datum3 = LocalDate.of(2022, 3, 23);
        LocalTime beginn3 = LocalTime.of(12, 30);
        LocalTime end3 = LocalTime.of(13, 0);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);
        Urlaubzeit urlaubzeit3 = new Urlaubzeit(datum3, beginn3, end3);

        urlaubzeitList.add(urlaubzeit1);
        urlaubzeitList.add(urlaubzeit2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit3, urlaubzeitList);

        assertThat(i).isEqualTo(32);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test18() {

        ArrayList<Urlaubzeit> urlaubzeitList = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(13, 0);
        LocalTime end2 = LocalTime.of(13, 30);


        LocalDate datum3 = LocalDate.of(2022, 3, 23);
        LocalTime beginn3 = LocalTime.of(10, 0);
        LocalTime end3 = LocalTime.of(11, 0);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);
        Urlaubzeit urlaubzeit3 = new Urlaubzeit(datum3, beginn3, end3);

        urlaubzeitList.add(urlaubzeit1);
        urlaubzeitList.add(urlaubzeit2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit3, urlaubzeitList);

        assertThat(i).isEqualTo(31);

    }

    @Test
    @DisplayName("Dauer der Klausur")
    void test19() {

        ArrayList<Urlaubzeit> urlaubzeitList = new ArrayList<>();

        LocalDate datum1 = LocalDate.of(2022, 3, 23);
        LocalTime beginn1 = LocalTime.of(9, 30);
        LocalTime end1 = LocalTime.of(10, 30);

        LocalDate datum2 = LocalDate.of(2022, 3, 23);
        LocalTime beginn2 = LocalTime.of(13, 0);
        LocalTime end2 = LocalTime.of(13, 30);


        LocalDate datum3 = LocalDate.of(2022, 3, 23);
        LocalTime beginn3 = LocalTime.of(10, 0);
        LocalTime end3 = LocalTime.of(10, 30);


        Urlaubzeit urlaubzeit1 = new Urlaubzeit(datum1, beginn1, end1);
        Urlaubzeit urlaubzeit2 = new Urlaubzeit(datum2, beginn2, end2);
        Urlaubzeit urlaubzeit3 = new Urlaubzeit(datum3, beginn3, end3);

        urlaubzeitList.add(urlaubzeit1);
        urlaubzeitList.add(urlaubzeit2);

        Ordnung ordnung = new Ordnung();
        int i = ordnung.pruefung(urlaubzeit3, urlaubzeitList);

        assertThat(i).isEqualTo(21);

    }

}
