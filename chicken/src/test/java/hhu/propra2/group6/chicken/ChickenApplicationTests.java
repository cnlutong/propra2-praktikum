package hhu.propra2.group6.chicken;

import hhu.propra2.group6.chicken.controller.student.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class ChickenApplicationTests {


    private final StudentController controller;

    private final DataSource dataSource;

    @Autowired
    ChickenApplicationTests(StudentController controller, DataSource dataSource) {
        this.controller = controller;
        this.dataSource = dataSource;
    }

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
