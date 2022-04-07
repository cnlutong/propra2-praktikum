package hhu.propra2.group6.chicken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChickenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChickenApplication.class, args);
    }

//
//    @Bean
//    ApplicationRunner applicationRunner(StudentService studentService,
//                                        HolidayService holidayService,
//                                        ExamService examService,
//                                        ExamExistService examExistService) {

//        return args -> {
//            Exam exam = new Exam(null, "LOL",
//                    new TimePeriod(LocalDateTime.of(2022, 3, 22, 9, 30),
//                            LocalDateTime.of(2022, 3, 22, 10, 0)),
//                    true, null);
//
//            exam.setLsfid(123L);
//            System.out.println(exam);
//            examExistService.save(exam);
//
//            System.out.println("isExist: "+examExistService.isExist(123L));
//            System.out.println("get: "+ examExistService.findByLsfid(123L));
//        };
//    }

//    @Bean
//    ApplicationRunner applicationRunner(StudentService studentService,
//                                        HolidayService holidayService,
//                                        ExamService examService) {
//        return args -> {
//            Holiday holiday = new Holiday(new TimePeriod(
//                    LocalDateTime.of(2022, 3, 22, 9, 30),
//                    LocalDateTime.of(2022, 3, 22, 10, 0)
//            ), 100L);
//
//            Holiday holiday1 = new Holiday(new TimePeriod(
//                    LocalDateTime.of(2022, 3, 24, 11, 30),
//                    LocalDateTime.of(2022, 3, 24, 13, 30)
//            ), 100L);
//
//            Holiday holiday2 = new Holiday(new TimePeriod(
//                    LocalDateTime.of(2022, 3, 23, 11, 30),
//                    LocalDateTime.of(2022, 3, 23, 12, 30)
//            ), 100L);
//
//            Exam exam = new Exam(1000L, "LOL", new TimePeriod(
//                    LocalDateTime.of(2022, 3, 22, 10, 0),
//                    LocalDateTime.of(2022, 3, 22, 11, 0)
//            ), false, 100L);
//
//            Exam exam2 = new Exam(1000L, "LOL", new TimePeriod(
//                    LocalDateTime.of(2022, 3, 22, 10, 0),
//                    LocalDateTime.of(2022, 3, 22, 11, 0)
//            ), false, 101L);
//
//            Exam exam1 = new Exam(1001L, "PUBG", new TimePeriod(
//                    LocalDateTime.of(2022, 3, 21, 10, 0),
//                    LocalDateTime.of(2022, 3, 21, 11, 0)
//            ), false, 100L);
//
//            List<Holiday> holidays = new ArrayList<>(List.of(holiday));
//            List<Exam> exams = new ArrayList<>(List.of(exam));
//
//            Student student = new Student(null, 100L, "Zhang San");
//            Student student1 = new Student(null, 101L, "Li Si");
//            Student student2 = new Student(null, 102L, "Wang Wu");
//
//
//            studentService.clear();
//            holidayService.clear();
//            examService.clear();
//
//            studentService.save(student, new ArrayList<>(), new ArrayList<>());
//            studentService.save(student1, new ArrayList<>(), new ArrayList<>());
//
//
////            System.out.println(holidayService.insertRed(studentService.getStudent(100L,examService,holidayService), holiday));
////            System.out.println(holidayService.insertRed(studentService.getStudent(100L,examService,holidayService), holiday1));
//
//
//            //Zhang San
//            Student studentDu = studentService.getStudent(100L,
//                    examService,
//                    holidayService);
//
//            //Li Si
//            Student studentDu1 = studentService.getStudent(101L,
//                    examService,
//                    holidayService);
//
//
//
//            System.out.println(examService.insertGreen(studentDu, exam));
//            System.out.println(examService.insertGreen(studentDu, exam1));
//            System.out.println(holidayService.insertRed(studentDu,holiday));
//
//            System.out.println(examService.insertGreen(studentDu1, exam2));
//
////            System.out.println(examService.deleteGreen(studentDu,exam1));
//
//            System.out.println("Zhang San Calenday");
//            for (int i = 0; i < studentDu.getMyCalendar().size(); i++) {
//                System.out.println(studentDu.getMyCalendar().get(i));
//            }
//
//            System.out.println("Zhang San Holiday");
//            for (int i = 0; i < studentDu.getMyHoliday().size(); i++) {
//                System.out.println(studentDu.getMyHoliday().get(i));
//            }
//
//            System.out.println("Zhang San Exam");
//            for (int i = 0; i < studentDu.getMyExam().size(); i++) {
//                System.out.println(studentDu.getMyExam().get(i));
//            }
//
//            System.out.println("Li Si Exam");
//            for (int i = 0; i < studentDu1.getMyExam().size(); i++) {
//                System.out.println(studentDu1.getMyExam().get(i));
//            }

//        };
//    }

}
