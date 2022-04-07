package hhu.propra2.group6.chicken.application.service;

import hhu.propra2.group6.chicken.application.repository.LogRepository;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void saveInsertExamLog(Long studentid, String name, String examName, CurrentTime currentTime) {

        String action = currentTime.now() + " " + name + " hat " + examName + " klausur angemeldet.";
        logRepository.save("Exam registration", studentid, name, action, currentTime);
    }


    public void saveDeleteExamLog(Long studentid, String name, String examName, CurrentTime currentTime) {
        String action = currentTime.now().toString() + " " + name + " hat " + examName + " klausur abgemeldet.";
        logRepository.save("Cancel exam registration", studentid, name, action, currentTime);
    }

    public void saveInsertHoliday(Long studentid, String name, String holidayString, CurrentTime currentTime) {
        String action = currentTime.now().toString() + " " + name + " hat ein Urlaub " + holidayString + " klausur abgemeldet.";
        logRepository.save("Holiday registration", studentid, name, action, currentTime);
    }

    public void saveDeleteHoliday(Long studentid, String name, String holidayString, CurrentTime currentTime) {
        String action = currentTime.now().toString() + " " + name + " hat ein Urlaub " + holidayString + " klausur abgemeldet.";
        logRepository.save("Cancel holiday registration", studentid, name, action, currentTime);
    }

    public void saveCreateExam(Long studentid, String name, String examName, CurrentTime currentTime) {
        String action = currentTime.now().toString() + " " + name + " hat " + examName + " klausur hergestellten.";
        logRepository.save("Exam creation", studentid, name, action, currentTime);
    }

    public void saveCreateStudent(Long studentid, String name, CurrentTime currentTime) {
        String action = currentTime.now().toString() + " " + name + " erfolgreich registriert.";
        logRepository.save("Student creation", studentid, name, action, currentTime);
    }

}
