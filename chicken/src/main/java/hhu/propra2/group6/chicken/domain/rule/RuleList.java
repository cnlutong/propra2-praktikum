package hhu.propra2.group6.chicken.domain.rule;

import hhu.propra2.group6.chicken.domain.time.DefaultTime;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class RuleList {


    public static ArrayList<OneRule> creatDefaultRuleList() {
        ArrayList<OneRule> defaultRuleList = new ArrayList<>();

        OneRule oneRule0 = (t1, t2, currentTime) -> {
            if ((t1.getMinute() % DefaultTime.getBlockSizeOfMinuten() != 0) || (t2.getMinute() % DefaultTime.getBlockSizeOfMinuten() != 0)) {
//            return "输入的请假时间违规, 必须为一刻钟的倍数";
                return "Die eingegebene Zeit muss in Vielfachen einer Viertelstunde angegeben werden.";
            }
            return "ok";
        };

        OneRule oneRule1 = (t1, t2, currentTime) -> {
            if (!((t1.isAfter(DefaultTime.getBegin().minusMinutes(1))) && (t2.isBefore(DefaultTime.getEnd().plusMinutes(1))))) {
//            return "输入的请假时间违规, 不符合PB开始或结束时间";
                return "Die für den Urlaub eingegebene Zeit stimmt nicht mit der Anfangs- oder Endzeit des PB überein";
            }
            return "ok";
        };

        OneRule oneRule2 = (t1, t2, currentTime) -> {
            if (t1.getDayOfMonth() == currentTime.now().getDayOfMonth() && t1.getDayOfMonth() == currentTime.now().getDayOfMonth()) {
//            return "输入的请假时间违规, 请假时间必须在当天之后";
                return "Die eingetragene Urlaubszeit ist unregelmäßig, der Urlaub muss nach dem Tag genommen werden";
            }
            return "ok";
        };

        OneRule oneRule3 = (t1, t2, currentTime) -> {
            if (((t1.isBefore(currentTime.now()) && (t2.isBefore(currentTime.now()))))) {
//            return "输入的请假时间违规, 请假时间必须在当天之后";
                return "Die eingetragene Urlaubszeit ist unregelmäßig, der Urlaub muss nach dem Tag genommen werden";
            }
            return "ok";
        };

        OneRule oneRule4 = (t1, t2, currentTime) -> {
            if (!((LocalTime.of(t1.getHour(), t1.getMinute()).isAfter(DefaultTime.getBeginLocalTime().minusMinutes(1))) && (LocalTime.of(t2.getHour(), t2.getMinute()).isBefore(DefaultTime.getEndLocalTime().plusMinutes(1))))) {
//            return "输入的请假时间违规, 不符合每天开始或结束时间";
                return "Die für den Urlaub eingegebene Zeit stimmt nicht mit der Anfangs- oder Endzeit des Tages überein";
            }
            return "ok";
        };

        OneRule oneRule5 = (t1, t2, currentTime) -> {
            if (!(Duration.between(t1, t2).toMinutes() <= DefaultTime.getMaxPartHolidayFromOneDayOfMinuten() || Duration.between(t1, t2).toMinutes() == DefaultTime.getMaxHolidayOfMinuten())) {
//            return "输入的请假时间违规，不符合请假的长度限制";
                return "Die für den Urlaub eingetragene Zeit ist unregelmäßig und entspricht nicht der Höchstdauer des Urlaubs";
            }
            return "ok";
        };

        OneRule oneRule6 = (t1, t2, currentTime) -> {
            DayOfWeek dayOfWeek = t1.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
//            return "都周末了，你还请个JB假";
                return "Es ist Wochenende, Sie müssen sich nicht freinehmen";
            }
            return "ok";
        };

        defaultRuleList.add(oneRule0);
        defaultRuleList.add(oneRule1);
        defaultRuleList.add(oneRule2);
        defaultRuleList.add(oneRule3);
        defaultRuleList.add(oneRule4);
        defaultRuleList.add(oneRule5);
        defaultRuleList.add(oneRule6);

        return defaultRuleList;
    }
}
