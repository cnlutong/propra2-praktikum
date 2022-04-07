package hhu.propra2.gruppe6.chicken.applicationservice;

import hhu.propra2.gruppe6.chicken.applicationservice.repository.StudentRepository;
import hhu.propra2.gruppe6.chicken.domain.time.CurrentTime;
import hhu.propra2.gruppe6.chicken.domain.time.Timeperiod;

import java.time.LocalDateTime;

public class ChickenService {
    private final StudentRepository studentRepository;

    public ChickenService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public String ApplyForAHoliday(LocalDateTime l1,LocalDateTime l2){

        Timeperiod timeperiod = new Timeperiod(l1, l2);
        CurrentTime currentTime = new CurrentTime();
        return studentRepository.findStudent().simplySetHappy(timeperiod,currentTime);

    }

    public boolean ApplyForAExam(LocalDateTime l1,LocalDateTime l2){

        Timeperiod timeperiod = new Timeperiod(l1, l2);
        CurrentTime currentTime = new CurrentTime();
        return studentRepository.findStudent().simplySetNotHappy(studentRepository.findExamsTakenByStudents(),currentTime);

    }


}
