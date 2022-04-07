package hhu.propra2.group6.chicken.application.service;

import hhu.propra2.group6.chicken.application.repository.LogRepository;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class LogServiceTest {

    @Test
    void saveInsertExamLog() {
        LogRepository logrepo = mock(LogRepository.class);
        LogService logService = new LogService(logrepo);
        CurrentTime c = mock(CurrentTime.class);
        when(c.now()).thenReturn(LocalDateTime.of(2022, 3, 24, 13, 0));
        logService.saveInsertExamLog(1L, "Tong", "LOL", c);

        verify(logrepo).save("Exam registration", 1L, "Tong", LocalDateTime.of(2022, 3, 24, 13, 0) + " " + "Tong" + " hat " + "LOL" + " klausur angemeldet.", c);

    }

    @Test
    void saveDeleteExamLog() {
        LogRepository logrepo = mock(LogRepository.class);
        LogService logService = new LogService(logrepo);
        CurrentTime c = mock(CurrentTime.class);
        when(c.now()).thenReturn(LocalDateTime.of(2022, 3, 24, 13, 0));
        logService.saveDeleteExamLog(1L, "Tong", "LOL", c);

        verify(logrepo).save("Cancel exam registration", 1L, "Tong", LocalDateTime.of(2022, 3, 24, 13, 0) + " " + "Tong" + " hat " + "LOL" + " klausur abgemeldet.", c);

    }

    @Test
    void saveInsertHoliday() {
        LogRepository logrepo = mock(LogRepository.class);
        LogService logService = new LogService(logrepo);
        CurrentTime c = mock(CurrentTime.class);
        when(c.now()).thenReturn(LocalDateTime.of(2022, 3, 24, 13, 0));
        logService.saveInsertHoliday(1L, "Tong", "LOL", c);

        verify(logrepo).save("Holiday registration", 1L, "Tong", LocalDateTime.of(2022, 3, 24, 13, 0) + " " + "Tong" + " hat ein Urlaub " + "LOL" + " klausur abgemeldet.", c);

    }

    @Test
    void saveDeleteHoliday() {
        LogRepository logrepo = mock(LogRepository.class);
        LogService logService = new LogService(logrepo);
        CurrentTime c = mock(CurrentTime.class);
        when(c.now()).thenReturn(LocalDateTime.of(2022, 3, 24, 13, 0));
        logService.saveDeleteHoliday(1L, "Tong", "LOL", c);

        verify(logrepo).save("Cancel holiday registration", 1L, "Tong", LocalDateTime.of(2022, 3, 24, 13, 0) + " " + "Tong" + " hat ein Urlaub " + "LOL" + " klausur abgemeldet.", c);

    }

    @Test
    void saveCreateExam() {
        LogRepository logrepo = mock(LogRepository.class);
        LogService logService = new LogService(logrepo);
        CurrentTime c = mock(CurrentTime.class);
        when(c.now()).thenReturn(LocalDateTime.of(2022, 3, 24, 13, 0));
        logService.saveCreateExam(1L, "Tong", "LOL", c);

        verify(logrepo).save("Exam creation", 1L, "Tong", LocalDateTime.of(2022, 3, 24, 13, 0) + " " + "Tong" + " hat " + "LOL" + " klausur hergestellten.", c);

    }

    @Test
    void saveCreateStudent() {
        LogRepository logrepo = mock(LogRepository.class);
        LogService logService = new LogService(logrepo);
        CurrentTime c = mock(CurrentTime.class);
        when(c.now()).thenReturn(LocalDateTime.of(2022, 3, 24, 13, 0));
        logService.saveCreateStudent(1L, "Tong", c);

        verify(logrepo).save("Student creation", 1L, "Tong", LocalDateTime.of(2022, 3, 24, 13, 0) + " " + "Tong" + " erfolgreich registriert.", c);

    }
}