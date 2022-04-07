package hhu.propra2.gruppe6.chicken.domain.log;

import hhu.propra2.gruppe6.chicken.domain.date.exam.Exam;
import hhu.propra2.gruppe6.chicken.domain.student.Student;
import hhu.propra2.gruppe6.chicken.domain.time.CurrentTime;
import hhu.propra2.gruppe6.chicken.domain.time.Timeperiod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogListTest {

    @BeforeEach
    public void initEach() {
        LogList.getLoglist().clear();
    }


    @Test
//    @DisplayName("显示1条请假记录的Log")
    @DisplayName("Log showing 1 leave record")
    void test01() {
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));
        UUID uuid = UUID.randomUUID();
        Student student = new Student("Tong", uuid, 6);
        student.simplySetHappy(new Timeperiod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 12, 00)), currentTime);

        System.out.println(LogList.getLoglist());
        assertThat(LogList.getLoglist().size()).isEqualTo(1);
    }


    @Test
//    @DisplayName("显示多条请假记录的Log")
    @DisplayName("Log showing multiple leave records")
    void test02() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student("Tong1", UUID.randomUUID(), 6);
        student.simplySetHappy(new Timeperiod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 12, 00)), currentTime);

        Student student1 = new Student("Tong2", UUID.randomUUID(), 6);
        student1.simplySetHappy(new Timeperiod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 12, 00)), currentTime);

        System.out.println(LogList.getLoglist());

        assertThat(LogList.getLoglist().size()).isEqualTo(2);

    }


    @Test
    @DisplayName("两条连续的log")
    void test03() {
        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student("Tong", UUID.randomUUID(), 6);
        student.simplySetHappy(new Timeperiod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 00)), currentTime);
        student.simplySetHappy(new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 00), LocalDateTime.of(2022, 3, 15, 10, 30)), currentTime);

        System.out.println(LogList.getLoglist());

        assertThat(LogList.getLoglist().size()).isEqualTo(2);

    }

    @Test
    @DisplayName("删除一条请假")
    void test04() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student("Tong", UUID.randomUUID(), 6);
        student.simplySetHappy(new Timeperiod(LocalDateTime.of(2022, 3, 15, 9, 30), LocalDateTime.of(2022, 3, 15, 10, 00)), currentTime);
        student.simplySetHappy(new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 00), LocalDateTime.of(2022, 3, 15, 10, 30)), currentTime);

        student.simplyDeleteHappy(new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 00), LocalDateTime.of(2022, 3, 15, 10, 30)), currentTime);


        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday().get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");
        System.out.println(LogList.getLoglist());


        assertThat(student.getHappyList().size()).isEqualTo(1);
        assertThat(LogList.getLoglist().size()).isEqualTo(3);

    }

    @Test
    @DisplayName("2中删除一条考试")
    void test05() {

        CurrentTime currentTime = mock(CurrentTime.class);
        when(currentTime.now()).thenReturn(LocalDateTime.of(2022, 3, 7, 9, 29));

        Student student = new Student("Tong", UUID.randomUUID(), 6);
        Exam exam0 = new Exam("Info", new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 0), LocalDateTime.of(2022, 3, 15, 11, 0)), false, 123);
        Exam exam1 = new Exam("Info", new Timeperiod(LocalDateTime.of(2022, 3, 16, 10, 0), LocalDateTime.of(2022, 3, 16, 11, 0)), false, 456);

        student.simplySetNotHappy(exam0, currentTime);
        student.simplySetNotHappy(exam1, currentTime);
        student.simplyDeleteNotHappy(exam1);

        System.out.println("-----------");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(student.getCalendar().get(i).getOneday().get(j).getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("-----------");
        System.out.println(LogList.getLoglist());

        assertThat(student.getHappyList().size()).isEqualTo(0);
        assertThat(LogList.getLoglist().size()).isEqualTo(3);
    }


}
