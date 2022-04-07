package hhu.propra2.gruppe6.chicken.domain.student;

import hhu.propra2.gruppe6.chicken.domain.date.exam.Exam;
import hhu.propra2.gruppe6.chicken.domain.time.CurrentTime;
import hhu.propra2.gruppe6.chicken.domain.time.Timeperiod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentTest {

    @Test
    @DisplayName("给学生添加一个考试")
    void test01() {
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 00)
                , LocalDateTime.of(2022, 3, 15, 11, 00))
                , true, 12345);

        Student student = new Student(null, null, 0);

        student.simplySetNotHappy(exam,currentTime);

        System.out.println(student.getExams().get(0));
        assertThat(student.getExams().size()).isEqualTo(1);

    }


    @Test
    @DisplayName("请假后，考试给请假顶了")
    void test02() {
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 00)
                , LocalDateTime.of(2022, 3, 15, 11, 00))
                , true, 12345);

        Student student = new Student(null, null, 0);
        Timeperiod timepe = new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 30), LocalDateTime.of(2022, 3, 15, 11, 00));

        student.simplySetHappy(timepe,currentTime);
        student.simplySetNotHappy(exam,currentTime);

        System.out.println(student.getExams().get(0));
//        System.out.println(student.getNotHappyList().get(0));
        assertThat(student.getExams().size()).isEqualTo(1);
        assertThat(student.getHappyList().size()).isEqualTo(0);

    }

    @Test
    @DisplayName("请了考试后，在考试时间又请了假，请不上SB")
    void test03() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 00)
                , LocalDateTime.of(2022, 3, 15, 11, 00))
                , true, 12345);

        Student student = new Student(null, null, 0);
        Timeperiod timepe = new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 30), LocalDateTime.of(2022, 3, 15, 11, 00));


        student.simplySetNotHappy(exam,currentTime);
        student.simplySetHappy(timepe,currentTime);


        System.out.println(student.getExams().get(0));

        assertThat(student.getExams().size()).isEqualTo(1);
        assertThat(student.getHappyList().size()).isEqualTo(0);

    }

    @Test
    @DisplayName("考试的结束在1点半之后")
    void test04() {
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 11, 00)
                , LocalDateTime.of(2022, 3, 15, 14, 00))
                , true, 12345);

        Student student = new Student(null, null, 0);
        Timeperiod timepe = new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 30), LocalDateTime.of(2022, 3, 15, 11, 00));

        student.simplySetHappy(timepe,currentTime);
        student.simplySetNotHappy(exam,currentTime);

        System.out.println(student.getExams().get(0));

        assertThat(student.getExams().size()).isEqualTo(1);
        assertThat(student.getHappyList().size()).isEqualTo(0);

    }


    @Test
    @DisplayName("一天全是考试")
    void test05() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 8, 00)
                , LocalDateTime.of(2022, 3, 15, 14, 00))
                , true, 12345);

        Student student = new Student(null, null, 0);
//        Timeperiod timepe = new Timeperiod(LocalDateTime.of(2022,3,15,10,30), LocalDateTime.of(2022,3,15,11,00));

//        student.simplySetHappy(timepe);
        student.simplySetNotHappy(exam,currentTime);

        System.out.println(student.getExams().get(0));

        assertThat(student.getExams().size()).isEqualTo(1);
//        assertThat(student.getHappyList().size()).isEqualTo(0);

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
    @DisplayName("前后两个假 中间夹着短暂的线上考试 和上课， 累死我了")
    void test06() {
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 11, 00)
                , LocalDateTime.of(2022, 3, 15, 11, 15))
                , false, 12345);

        Student student = new Student(null, null, 0);
        Timeperiod timepe = new Timeperiod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 00));
        Timeperiod timepe1 = new Timeperiod(LocalDateTime.of(2022, 3, 15, 13, 00), LocalDateTime.of(2022, 3, 15, 13, 30));

        student.simplySetHappy(timepe,currentTime);
        student.simplySetHappy(timepe1,currentTime);
        student.simplySetNotHappy(exam,currentTime);

        System.out.println(student.getExams().get(0));

        assertThat(student.getExams().size()).isEqualTo(1);
        assertThat(student.getHappyList().size()).isEqualTo(2);

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
    @DisplayName("已经有两个假了，然后来了个考试吧第二个假吃了")
    void test07() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 13, 00)
                , LocalDateTime.of(2022, 3, 15, 13, 15))
                , true, 12345);

        Student student = new Student(null, null, 0);
        Timeperiod timepe = new Timeperiod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 00));
        Timeperiod timepe1 = new Timeperiod(LocalDateTime.of(2022, 3, 15, 13, 00), LocalDateTime.of(2022, 3, 15, 13, 30));

        student.simplySetHappy(timepe,currentTime);
        student.simplySetHappy(timepe1,currentTime);
        student.simplySetNotHappy(exam,currentTime);

        System.out.println(student.getExams().get(0));

        assertThat(student.getExams().size()).isEqualTo(1);
        assertThat(student.getHappyList().size()).isEqualTo(1);

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
    @DisplayName("已经有两个假了，然后来了个考试吧第二个假吃了, 我再请假")
    void test08() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 13, 00)
                , LocalDateTime.of(2022, 3, 15, 13, 15))
                , true, 12345);

        Student student = new Student(null, null, 0);
        Timeperiod timepe = new Timeperiod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 00));
        Timeperiod timepe1 = new Timeperiod(LocalDateTime.of(2022, 3, 15, 13, 00), LocalDateTime.of(2022, 3, 15, 13, 30));
        Timeperiod timepe2 = new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 00), LocalDateTime.of(2022, 3, 15, 11, 0));

        student.simplySetHappy(timepe,currentTime);
        student.simplySetHappy(timepe1,currentTime);
        student.simplySetNotHappy(exam,currentTime);
        student.simplySetHappy(timepe2,currentTime);


//        System.out.println(student.getExams().get(0));

        assertThat(student.getExams().size()).isEqualTo(1);
        assertThat(student.getHappyList().size()).isEqualTo(1);

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
    @DisplayName("不同的天 两个考试")
    void test09() {
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 13, 00)
                , LocalDateTime.of(2022, 3, 15, 13, 15))
                , true, 12345);


        Exam exam1 = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 16, 13, 00)
                , LocalDateTime.of(2022, 3, 16, 13, 15))
                , true, 12345);

        Student student = new Student(null, null, 0);
//        Timeperiod timepe = new Timeperiod(LocalDateTime.of(2022,3,15,9,30), LocalDateTime.of(2022,3,15,10,00));
//        Timeperiod timepe1 = new Timeperiod(LocalDateTime.of(2022,3,15,13,00), LocalDateTime.of(2022,3,15,13,30));
//        Timeperiod timepe2 = new Timeperiod(LocalDateTime.of(2022,3,15,10,00), LocalDateTime.of(2022,3,15,11,0));

//        student.simplySetHappy(timepe);
//        student.simplySetHappy(timepe1);
        student.simplySetNotHappy(exam,currentTime);
        student.simplySetNotHappy(exam1,currentTime);
//        student.simplySetHappy(timepe2,currentTime);


//        System.out.println(student.getExams().get(0));

        assertThat(student.getExams().size()).isEqualTo(2);
//        assertThat(student.getHappyList().size()).isEqualTo(1);

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
    @DisplayName("过去时间的考试")
    void test10() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 6, 13, 00)
                , LocalDateTime.of(2022, 3, 6, 13, 15))
                , true, 12345);

        Student student = new Student(null, null, 0);

        student.simplySetNotHappy(exam,currentTime);

        assertThat(student.getExams().size()).isEqualTo(0);

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
    @DisplayName("当天的假")
    void test11() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 11, 8, 29));

        Student student = new Student(null, null, 0);

        Timeperiod timepe = new Timeperiod(LocalDateTime.of(2022, 3, 11, 10, 00), LocalDateTime.of(2022, 3, 11, 11, 0));
        String s = student.simplySetHappy(timepe,currentTime);



        assertThat(student.getHappyList().size()).isEqualTo(0);

        System.out.println(s);
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
    @DisplayName("不是15倍数的时间")
    void test12() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student(null, null, 0);

        Timeperiod timepe = new Timeperiod(LocalDateTime.of(2022, 3, 12, 10, 5), LocalDateTime.of(2022, 3, 12, 11, 28));
        String s = student.simplySetHappy(timepe,currentTime);

        assertThat(student.getHappyList().size()).isEqualTo(0);

        System.out.println(s);
        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday().get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");

    }
}
