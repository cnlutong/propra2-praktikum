package hhu.propra2.group6.chicken.application.service;

import hhu.propra2.group6.chicken.application.repository.ExamRepository;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.student.Student;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final HolidayService holidayService;

    public ExamService(ExamRepository examRepository, HolidayService holidayService) {
        this.examRepository = examRepository;
        this.holidayService = holidayService;
    }

    public void save(Exam exam) {
        examRepository.save(exam);
    }

    public void delete(Exam exam) {
        examRepository.delete(exam);
    }

    public Exam findByStudentID(Long studentid) {
        return examRepository.findByStudentID(studentid);
    }

    public List<Exam> findAllByStudentID(Long studentid) {
        return examRepository.findAllByStudentID(studentid);
    }

    public Exam findById(Long id) {
        return examRepository.findById(id);
    }

    public Boolean insertGreen(Student student, Exam exam, CurrentTime currentTime) {
        if (student.insertGreen(exam, currentTime)) {
            save(exam);
            return true;
        }
        return false;
    }

    public Boolean deleteGreen(Student student, Exam exam, CurrentTime currentTime) {
        if (student.deleteGreen(exam, currentTime)) {
            delete(exam);

            if (!student.checkAfterDeleteExam(student.getMyCalendar(), exam.getTimePeriod())) {
                List<Holiday> myHolidayDeleteList = new ArrayList<>();
                for (Holiday holiday : student.getMyHoliday()) {
                    if (holiday.getTimePeriod().getL1().getDayOfYear() == exam.getTimePeriod().getL1().getDayOfYear()) {
                        myHolidayDeleteList.add(holiday);
                    }
                }
                for (Holiday holiday : myHolidayDeleteList) {
                    holidayService.delete(holiday);
                }
            }
            return true;
        }
        return false;
    }

    public void clear() {
        examRepository.clear();
    }
}
