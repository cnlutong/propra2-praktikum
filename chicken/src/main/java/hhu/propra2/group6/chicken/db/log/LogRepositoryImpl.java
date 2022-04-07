package hhu.propra2.group6.chicken.db.log;

import hhu.propra2.group6.chicken.application.repository.LogRepository;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import org.springframework.stereotype.Repository;

@Repository
public class LogRepositoryImpl implements LogRepository {

    DBLogRepository dbLogRepository;

    public LogRepositoryImpl(DBLogRepository dbLogRepository) {
        this.dbLogRepository = dbLogRepository;
    }

    @Override
    public void save(String type, Long studentid, String name, String action, CurrentTime currentTime) {
        dbLogRepository.save(LogDTO.creat(type, studentid, name, action, currentTime));
    }


}
