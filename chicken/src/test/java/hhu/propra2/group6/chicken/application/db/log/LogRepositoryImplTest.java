package hhu.propra2.group6.chicken.application.db.log;

import hhu.propra2.group6.chicken.db.log.DBLogRepository;
import hhu.propra2.group6.chicken.db.log.LogDTO;
import hhu.propra2.group6.chicken.db.log.LogRepositoryImpl;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LogRepositoryImplTest {

    @Test
    void save() {
        DBLogRepository dbLogRepository = mock(DBLogRepository.class);
        LogRepositoryImpl logRepository = new LogRepositoryImpl(dbLogRepository);
        CurrentTime currentTime = new CurrentTime();
        logRepository.save("abc", 123L, "tong", "...", currentTime);
        verify(dbLogRepository).save(LogDTO.creat("abc", 123L, "tong", "...", currentTime));
    }
}