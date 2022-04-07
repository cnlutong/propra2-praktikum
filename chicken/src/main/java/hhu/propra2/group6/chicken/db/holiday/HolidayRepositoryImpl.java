package hhu.propra2.group6.chicken.db.holiday;

import hhu.propra2.group6.chicken.application.repository.HolidayRepository;
import hhu.propra2.group6.chicken.domain.holiday.Holiday;
import hhu.propra2.group6.chicken.domain.student.Student;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class HolidayRepositoryImpl implements HolidayRepository {

    DBHolidayRepository dbHolidayRepository;

    public HolidayRepositoryImpl(DBHolidayRepository dbHolidayRepository) {
        this.dbHolidayRepository = dbHolidayRepository;
    }

    @Override
    public void save(Holiday holiday) {
        dbHolidayRepository.save(toEntity(holiday));
    }

    @Override
    public void delete(Holiday holiday) {
        dbHolidayRepository.deleteByInfo(holiday.getTimePeriod().getL1(),
                holiday.getTimePeriod().getL2(),
                holiday.getStudnetid());
    }

    @Override
    public void clear() {
        dbHolidayRepository.deleteAll();
    }

    @Override
    public void deleteAllByStudentid(Long oaid) {
        dbHolidayRepository.deleteAllByStudentid(oaid);
    }

    @Override
    public void saveAllCacheToDB(Student student) {
        for (Holiday holiday : student.getMyHoliday()) {
            save(holiday);
        }
    }

    @Override
    public Holiday findHolidayByID(Long id) {
        return toHoliday(Objects.requireNonNull(dbHolidayRepository.findById(id).orElse(null)));
    }

    @Override
    public List<Holiday> findAllByStudentID(Long studentid) {
        return dbHolidayRepository.findAllByStudentid(studentid).stream().map(this::toHoliday).collect(Collectors.toList());
    }

    public Holiday toHoliday(HolidayDTO holidayDTO) {
        return new Holiday(holidayDTO.getId(), new TimePeriod(holidayDTO.getBegin(), holidayDTO.getEnd()), holidayDTO.getStudentid());
    }

    public HolidayDTO toEntity(Holiday holiday) {
        return new HolidayDTO(null,
                holiday.getTimePeriod().getL1(),
                holiday.getTimePeriod().getL2(),
                holiday.getStudnetid());
    }

}
