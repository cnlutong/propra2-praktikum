package hhu.propra2.group6.chicken.domain.student;

import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.time.Block;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import hhu.propra2.group6.chicken.domain.time.DefaultTime;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentTest {

    @Test
    @DisplayName("Test initialization linked list")
    void test_01() {
        Student student = new Student(1L, 2L, "Tong");
        ArrayList<Block> calendar = student.getMyCalendar();
        int countnotwhite = 0;
        for (int i = 0; i < calendar.size(); i++) {
            if (!calendar.get(i).getColor().equals("white")) {
                countnotwhite++;
            }
        }
        assertThat(countnotwhite).isEqualTo(0);
    }

    @Test
    @DisplayName("Test setting the red block on the linked list")
    void test_02() {
        Student student = new Student(1L, 2L, "Tong");
        ArrayList<Block> calendar = student.getMyCalendar();

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 7, 0));

        student.insertRed(new TimePeriod(LocalDateTime.of(2022, 3, 8, 9, 30),
                LocalDateTime.of(2022, 3, 8, 10, 0)), currentTime);

        int sumred = student.getsumRed();

        assertThat(sumred).isEqualTo(30);
    }

    @Test
    @DisplayName("Test setting the red block on the linked list")
    void test_03() {

        Student student = new Student(1L, 2L, "Tong");
        ArrayList<Block> calendar = student.getMyCalendar();

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 6, 7, 0));

        student.insertRed(new TimePeriod(LocalDateTime.of(2022, 3, 7, 9, 30),
                LocalDateTime.of(2022, 3, 7, 10, 0)), currentTime);

        String color1 = student.getOneQuarterByTime(LocalDateTime.of(2022, 3, 7, 9, 30)).getColor();
        String color2 = student.getOneQuarterByTime(LocalDateTime.of(2022, 3, 7, 10, 0).minusMinutes(DefaultTime.getBlockSizeOfMinuten())).getColor();

        assertThat(color1).isEqualTo("red");
        assertThat(color2).isEqualTo("red");

    }

    @Test
    @DisplayName("Test if it was added successfully")
    void test_04() {
        Student student = new Student(1L, 2L, "Tong");
        ArrayList<Block> calendar = student.getMyCalendar();
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 20, 7, 0));

        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 0));
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 15, 10, 0), LocalDateTime.of(2022, 3, 15, 10, 30));

        String s = student.insertRed(timePeriod, currentTime);

        assertThat(s).isNotEqualTo("ok");
    }

    @Test
    @DisplayName("Tests take time off at the beginning and end of the day")
    void test_05() {

        Student student = new Student(1L, 2L, "Tong");
        ArrayList<Block> calendar = student.getMyCalendar();
        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 0));
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 15, 13, 0), LocalDateTime.of(2022, 3, 15, 13, 30));

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 7, 0));

        String s1 = student.insertRed(timePeriod, currentTime);

        String s2 = student.insertRed(timePeriod1, currentTime);

        List<Holiday> myHoliday = student.getMyHoliday();

        assertThat(s1).isEqualTo("ok");
        assertThat(s2).isEqualTo("ok");

        assertThat(myHoliday.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("The test takes three days off in a day and ends with breaks at the beginning and end")
    void test_06() {
        Student student = new Student(1L, 2L, "Tong");

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 7, 0));

        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 0));
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 15, 13, 0), LocalDateTime.of(2022, 3, 15, 13, 30));
        TimePeriod timePeriod2 = new TimePeriod(LocalDateTime.of(2022, 3, 15, 10, 0), LocalDateTime.of(2022, 3, 15, 11, 0));

        String s1 = student.insertRed(timePeriod, currentTime);

        String s2 = student.insertRed(timePeriod1, currentTime);
        String s3 = student.insertRed(timePeriod2, currentTime);
        List<Holiday> myHoliday = student.getMyHoliday();

        assertThat(s1).isEqualTo("ok");
        assertThat(s2).isEqualTo("ok");
        assertThat(s3).isEqualTo("ok");

        assertThat(myHoliday.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test normal add a holiday record")
    void test_08() {
        Student student = new Student(1L, 2L, "Tong");
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 7, 0));
        ArrayList<Block> calendar = student.getMyCalendar();
        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 0));
        String s1 = student.insertRed(timePeriod, currentTime);
        List<Holiday> myHoliday = student.getMyHoliday();
        assertThat(s1).isEqualTo("ok");

        assertThat(myHoliday.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("Test adding two holiday records that can be combined")
    void test_09() {

        Student student = new Student(1L, 2L, "Tong");
        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 0));
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 15, 10, 0), LocalDateTime.of(2022, 3, 15, 10, 30));

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 7, 0));

        String s1 = student.insertRed(timePeriod, currentTime);
        String s2 = student.insertRed(timePeriod1, currentTime);
        List<Holiday> myHoliday = student.getMyHoliday();

        assertThat(s1).isEqualTo("ok");
        assertThat(s2).isEqualTo("ok");

        assertThat(myHoliday.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("The test first adds a holiday record, and then deletes it")
    void test_10() {

        Student student = new Student(1L, 2L, "Tong");

        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 0));

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 0, 0));

        student.insertRed(timePeriod, currentTime);
        student.deleteRed(timePeriod, currentTime);

        assertThat(student.getMyHoliday().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("The test first adds 2 days of holiday records, and then deletes one of them")
    void test_11() {

        Student student = new Student(1L, 2L, "Tong");
        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 0));
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 16, 9, 30), LocalDateTime.of(2022, 3, 16, 10, 0));

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 8, 0, 0));

        student.insertRed(timePeriod, currentTime);

        student.insertRed(timePeriod1, currentTime);
        student.deleteRed(timePeriod, currentTime);

        assertThat(student.getMyHoliday().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("The test first adds 2 days of holiday records, and then deletes one of them")
    void test_12() {

        Student student = new Student(1L, 2L, "Tong");

        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 0));
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 16, 9, 30), LocalDateTime.of(2022, 3, 16, 10, 0));

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 8, 0, 0));

        student.insertRed(timePeriod, currentTime);

        student.insertRed(timePeriod1, currentTime);
        student.deleteRed(timePeriod, currentTime);


        assertThat(student.getsumRed()).isEqualTo(30);
    }

    @Test
    @DisplayName("test add an exam")
    void test_13() {

        Student student = new Student(1L, 2L, "Tong");

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 8, 0, 0));

        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 2L);

        student.insertGreen(exam, currentTime);
        assertThat(student.getMyExam().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("test add an exam")
    void test_14() {

        Student student = new Student(1L, 2L, "Tong");


        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 8, 0, 0));
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 2L);
        student.insertGreen(exam, currentTime);

        assertThat(student.getMyExam().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test add an exam, then delete it")
    void test_15() {

        Student student = new Student(1L, 2L, "Tong");

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 8, 0, 0));
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 2L);
        student.insertGreen(exam, currentTime);

        student.deleteGreen(exam, currentTime);

        assertThat(student.getMyExam().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test add 2 exams and delete one of them")
    void test_16() {

        Student student = new Student(1L, 2L, "Tong");

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 8, 0, 0));
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 24, 10, 0),
                LocalDateTime.of(2022, 3, 24, 11, 0)), false, 2L);
        student.insertGreen(exam, currentTime);


        Exam exam1 = new Exam(100001L, "PUBG", new TimePeriod(LocalDateTime.of(2022, 3, 23, 10, 0),
                LocalDateTime.of(2022, 3, 23, 11, 0)), false, 2L);
        student.insertGreen(exam1, currentTime);
        student.insertGreen(exam, currentTime);
        student.deleteGreen(exam, currentTime);

        assertThat(student.getMyExam().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Tests add 1 exam and then add a vacation at the same time. The result is only the record of the exam")
    void test_17() {

        Student student = new Student(1L, 2L, "Tong");

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 8, 0, 0));
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 21, 10, 0),
                LocalDateTime.of(2022, 3, 21, 11, 0)), false, 3L);
        student.insertGreen(exam, currentTime);

        student.insertRed(new TimePeriod(LocalDateTime.of(2022, 3, 21, 9, 30),
                LocalDateTime.of(2022, 3, 21, 10, 0)), currentTime);

        assertThat(student.getMyHoliday().size()).isEqualTo(0);
        assertThat(student.getMyExam().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Add 1 vacation to the test, then add another exam at the same time. The result is only the record of the exam")
    void test_18() {

        Student student = new Student(1L, 2L, "Tong");

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 8, 0, 0));

        student.insertRed(new TimePeriod(LocalDateTime.of(2022, 3, 21, 9, 30),
                LocalDateTime.of(2022, 3, 21, 10, 0)), currentTime);
        Exam exam = new Exam(100000L, "LOL", new TimePeriod(LocalDateTime.of(2022, 3, 21, 10, 0),
                LocalDateTime.of(2022, 3, 21, 11, 0)), false, 3L);
        student.insertGreen(exam, currentTime);

        assertThat(student.getMyHoliday().size()).isEqualTo(0);
        assertThat(student.getMyExam().size()).isEqualTo(1);
    }


    @Test
    @DisplayName("Must have 90 minutes of class time during the day")
    void test_19() {

        Student student = new Student(1L, 2L, "Tong");

        TimePeriod timePeriod = new TimePeriod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 0));
        TimePeriod timePeriod1 = new TimePeriod(LocalDateTime.of(2022, 3, 15, 12, 30), LocalDateTime.of(2022, 3, 15, 13, 30));
        TimePeriod timePeriod2 = new TimePeriod(LocalDateTime.of(2022, 3, 15, 10, 0), LocalDateTime.of(2022, 3, 15, 11, 30));

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 8, 0, 0));

        student.insertRed(timePeriod, currentTime);
        student.insertRed(timePeriod1, currentTime);
        student.insertRed(timePeriod2, currentTime);

        assertThat(student.getsumRed()).isEqualTo(90);
    }
}