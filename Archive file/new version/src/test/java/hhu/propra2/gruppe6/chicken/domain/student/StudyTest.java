package hhu.propra2.gruppe6.chicken.domain.student;

import hhu.propra2.gruppe6.chicken.domain.time.CurrentTime;
import hhu.propra2.gruppe6.chicken.domain.time.Timeperiod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudyTest {

    @Test
    @DisplayName("01")
    void test01() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 10, 10, 00);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 10, 13, 00);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 10, 13, 30);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday().get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test02() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 10, 10, 00);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 10, 13, 00);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 10, 13, 30);

        LocalDateTime t5 = LocalDateTime.of(2022, 3, 10, 12, 30);
        LocalDateTime t6 = LocalDateTime.of(2022, 3, 10, 13, 15);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t5, t6), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test03() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 10, 10, 00);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 10, 13, 00);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 10, 13, 30);

        LocalDateTime t5 = LocalDateTime.of(2022, 3, 10, 12, 00);
        LocalDateTime t6 = LocalDateTime.of(2022, 3, 10, 12, 15);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t5, t6), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday().get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test04() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 10, 10, 00);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 10, 12, 00);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 10, 13, 30);

        LocalDateTime t5 = LocalDateTime.of(2022, 3, 10, 10, 00);
        LocalDateTime t6 = LocalDateTime.of(2022, 3, 10, 12, 00);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t5, t6), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday().get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test05() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 10, 10, 00);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 10, 10, 30);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 10, 12, 30);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday().get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test06() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 10, 9, 45);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 10, 9, 45);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 10, 10, 00);

        LocalDateTime t5 = LocalDateTime.of(2022, 3, 10, 12, 00);
        LocalDateTime t6 = LocalDateTime.of(2022, 3, 10, 12, 30);

        LocalDateTime t7 = LocalDateTime.of(2022, 3, 10, 12, 15);
        LocalDateTime t8 = LocalDateTime.of(2022, 3, 10, 12, 45);

        LocalDateTime t9 = LocalDateTime.of(2022, 3, 10, 12, 45);
        LocalDateTime t10 = LocalDateTime.of(2022, 3, 10, 13, 30);

        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t5, t6), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t6, t7), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t9, t10), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday().get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test07() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 10, 10, 00);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 10, 10, 00);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 10, 11, 00);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test08() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 10, 10, 00);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 11, 10, 00);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 11, 11, 00);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test09() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 10, 10, 00);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 10, 11, 30);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 10, 13, 30);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test10() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 6, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 6, 10, 00);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 6, 11, 30);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 6, 13, 30);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test11() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 13, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 13, 10, 00);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 13, 11, 30);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 13, 13, 30);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test12() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 8, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 8, 13, 30);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 9, 11, 30);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 9, 13, 30);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }

    @Test
    @DisplayName("01")
    void test13() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 8, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 8, 10, 30);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 9, 11, 30);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 9, 13, 30);

        LocalDateTime t5 = LocalDateTime.of(2022, 3, 10, 9, 30);
        LocalDateTime t6 = LocalDateTime.of(2022, 3, 10, 11, 0);

        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t5, t6), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");


        System.out.println(student.getHappyList());
    }

    @Test
    @DisplayName("01")
    void test14() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 8, 10, 00);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 8, 10, 30);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 8, 10, 30);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 8, 11, 00);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");


        System.out.println(student.getHappyList());
    }

    @Test
    @DisplayName("01")
    void test15() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 8, 10, 15);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 8, 10, 30);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 8, 10, 30);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 8, 11, 15);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");


        System.out.println(student.getHappyList());
    }

    @Test
    @DisplayName("01")
    void test16() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 8, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 8, 10, 30);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 8, 13, 00);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 8, 13, 30);


        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");


        System.out.println(student.getHappyList());
    }

    @Test
    @DisplayName("01")
    void test17() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 8, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 8, 10, 30);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 8, 13, 00);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 8, 13, 30);

        LocalDateTime t5 = LocalDateTime.of(2022, 3, 8, 12, 30);
        LocalDateTime t6 = LocalDateTime.of(2022, 3, 8, 13, 30);

        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t5, t6), currentTime));

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");


        System.out.println(student.getHappyList());
    }

    @Test
    @DisplayName("01")
    void test18() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 8, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 8, 10, 30);

        LocalDateTime t3 = LocalDateTime.of(2022, 3, 9, 13, 00);
        LocalDateTime t4 = LocalDateTime.of(2022, 3, 9, 13, 30);

        LocalDateTime t5 = LocalDateTime.of(2022, 3, 10, 13, 00);
        LocalDateTime t6 = LocalDateTime.of(2022, 3, 10, 13, 30);

        System.out.println(student.simplySetHappy(new Timeperiod(t1, t2), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t3, t4), currentTime));
        System.out.println(student.simplySetHappy(new Timeperiod(t5, t6), currentTime));
        System.out.println(student.getHappyList());

        student.simplyDeleteHappy(student.getHappyList().get(2), currentTime);


        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday()
                        .get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");


        System.out.println(student.getHappyList());
    }

    @Test
    @DisplayName("01")
    void test19() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 8, 9, 29));

        Student student = new Student(null, null, 0);
        LocalDateTime t1 = LocalDateTime.of(2022, 3, 8, 9, 30);
        LocalDateTime t2 = LocalDateTime.of(2022, 3, 8, 10, 30);

        String str = student.simplySetHappy(new Timeperiod(t1, t2), currentTime);


        assertThat(student.getHappyList().size()).isEqualTo(0);
        assertThat(str).isEqualTo("输入的请假时间违规, 请假时间必须在当天之后");

//        System.out.println(student.simplySet(new Timeperiod(t1, t2)));


//
//        System.out.println("-----------");
//        for (int i = 0; i < 15; i++) {
//            for (int j = 0; j < 16; j++) {
//                System.out.print(student.getCalendar().get(i).getoneday
//                .get(j).getColor()+" ");
//            }
//            System.out.println("");
//        }
//        System.out.println("-----------");


//        System.out.println(student.getTimeperiods());
    }
}
