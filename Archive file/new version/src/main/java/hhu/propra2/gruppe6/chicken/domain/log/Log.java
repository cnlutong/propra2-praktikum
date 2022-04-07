package hhu.propra2.gruppe6.chicken.domain.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Log {
    private final LocalDateTime operationTime;
    private final UUID uuid;
    private final String operator;
    private final String operate;


    public Log(UUID uuid, String operator, String operate) {
        this.operationTime = LocalDateTime.now();
        this.uuid = uuid;
        this.operator = operator;
        this.operate = operate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return
                "operationTime: " + operationTime.format(formatter) +
                        ", UUID: '" + uuid + '\'' +
                        ", operator: '" + operator + '\'' +
                        ", operate: '" + operate + '\'' +
                        "\n";
    }
}
