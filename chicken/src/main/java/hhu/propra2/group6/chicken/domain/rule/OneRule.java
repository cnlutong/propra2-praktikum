package hhu.propra2.group6.chicken.domain.rule;

import hhu.propra2.group6.chicken.domain.time.CurrentTime;

import java.time.LocalDateTime;

public interface OneRule {
    String check(LocalDateTime t1, LocalDateTime t2, CurrentTime currentTime);
}
