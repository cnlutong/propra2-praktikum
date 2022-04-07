package hhu.propra2.gruppe6.chicken.domain.log;

import java.util.ArrayList;
import java.util.UUID;

public class LogList {

    private static ArrayList<Log> loglist = new ArrayList<>();


    public static void simplyInsertLog(UUID uuid,String operator, String operate){
        loglist.add(new Log(uuid,operator, operate));
    }

    public static ArrayList<Log> getLoglist() {
        return loglist;
    }
}
