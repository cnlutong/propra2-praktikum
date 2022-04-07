package hhu.propra2.group6.chicken.application.repository;

import hhu.propra2.group6.chicken.domain.time.CurrentTime;

public interface LogRepository {
    void save(String typ, Long studentid, String name, String action, CurrentTime currentTime);
}
