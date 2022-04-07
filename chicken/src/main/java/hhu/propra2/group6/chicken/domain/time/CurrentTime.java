package hhu.propra2.group6.chicken.domain.time;

import java.time.LocalDateTime;

public class CurrentTime {

    private boolean default_time = true;
    private LocalDateTime localDateTime;

    //    如果您不想以当前时间作为系统的判断标准,请修改该方法
//    If you don't want to use the current time as the system's judgment standard, please modify this method.
    public LocalDateTime now() {
        if (this.default_time) {
            return LocalDateTime.now();

        }
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        this.default_time = false;
    }
}
