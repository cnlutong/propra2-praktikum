package hhu.propra2.group6.chicken.application.service;

import hhu.propra2.group6.chicken.application.repository.HolidayRepository;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.student.Student;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;

    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public void save(Holiday holiday) {
        holidayRepository.save(holiday);
    }

    public void delete(Holiday holiday) {
        holidayRepository.delete(holiday);
    }

    public List<Holiday> findAllByStudentID(Long studentid) {
        return holidayRepository.findAllByStudentID(studentid);
    }

    public String insertRed(Student student, Holiday holiday, CurrentTime currentTime) {

        if (student.insertRed(holiday.getTimePeriod(), currentTime).equals("ok")) {
            deleteAllByStudentid(student.getOaid());
            saveAllCacheToDB(student);
            return "insertRed complete";
        }
//         输出错误的信息
//        Ausgabe der falschen Nachricht
        return student.insertRed(holiday.getTimePeriod(), currentTime);
    }


    public String deleteRed(Student student, Holiday holiday, CurrentTime currentTime) {
        if (student.deleteRed(holiday.getTimePeriod(), currentTime).equals("ok")) {
            delete(holiday);
            return "deleteRed complete";
        }
        return student.deleteRed(holiday.getTimePeriod(), currentTime);
    }

    public void deleteAllByStudentid(Long oaid) {
        holidayRepository.deleteAllByStudentid(oaid);
    }

    public void saveAllCacheToDB(Student student) {
        holidayRepository.saveAllCacheToDB(student);
    }

    public void clear() {
        holidayRepository.clear();
    }


    public Holiday findById(Long urlaubid) {
        return holidayRepository.findHolidayByID(urlaubid);
    }
}
