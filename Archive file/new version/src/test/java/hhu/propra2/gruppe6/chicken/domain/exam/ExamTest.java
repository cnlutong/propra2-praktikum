package hhu.propra2.gruppe6.chicken.domain.exam;

import hhu.propra2.gruppe6.chicken.domain.date.exam.Exam;
import hhu.propra2.gruppe6.chicken.domain.time.Timeperiod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ExamTest {

    @Test
    @DisplayName("测试添加一Offline个考试，看考试放假是否合规")
    void test01() {
        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 00)
                , LocalDateTime.of(2022, 3, 15, 11, 00))
                , true, 12345);

        assertThat(exam.createfreeTimeperiod().getL1()).isEqualTo(LocalDateTime.of(2022, 3, 15, 9, 30));
        assertThat(exam.createfreeTimeperiod().getL2()).isEqualTo(LocalDateTime.of(2022, 3, 15, 13, 00));
    }

    @Test
    @DisplayName("测试添加一个Online考试，看考试放假是否合规")
    void test02() {
        Exam exam = new Exam(null
                , new Timeperiod(LocalDateTime.of(2022, 3, 15, 10, 00)
                , LocalDateTime.of(2022, 3, 15, 11, 00))
                , false, 12345);

        assertThat(exam.createfreeTimeperiod().getL1()).isEqualTo(LocalDateTime.of(2022, 3, 15, 9, 30));
        assertThat(exam.createfreeTimeperiod().getL2()).isEqualTo(LocalDateTime.of(2022, 3, 15, 11, 30));
    }
}
