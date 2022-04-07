package hhu.propra2.gruppe6.chicken.domain.student;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Ordnung {

    public boolean istErlaubt(Urlaubzeit urlaubzeit) { // 判断请假日期是否在实习期间且请假时间合规
        return (urlaubzeit.urlaubDauer() <= 150
                || urlaubzeit.urlaubDauer() == 240)
               && (urlaubzeit.urlaubDatum.isAfter(LocalDate.of(2022, 3, 6))
                   && urlaubzeit.urlaubDatum.isBefore(LocalDate.of(2022, 3, 26)));
    }

    private boolean istStartVonEinTag(Urlaubzeit urlaubzeit) { //是否是当天的开始时间
        return urlaubzeit.beginn.compareTo(LocalTime.of(9, 30)) == 0;
    }

    private boolean istEndVonEinTag(Urlaubzeit urlaubzeit) { //是否是当天的结束时间
        return urlaubzeit.end.compareTo(LocalTime.of(13, 30)) == 0;
    }

    private boolean istMehrAls90Min(Urlaubzeit urlaubzeit1, Urlaubzeit urlaubzeit2) { // 检查两次请假的间隔是否间隔大于90分钟
        return Duration.between(urlaubzeit1.getEnd(), urlaubzeit2.getBeginn()).toMinutes() >= 90;
    }

    private boolean istGeleichenTag(Urlaubzeit urlaubzeit1, Urlaubzeit urlaubzeit2) { //检查两个日期是否是同一天
        return urlaubzeit1.getUrlaubDatum().equals(urlaubzeit2.getUrlaubDatum());
    }

    public int dauer(LocalTime localTime1, LocalTime localTime2) {
        return (int) Duration.between(localTime1, localTime2).toMinutes();
    }

    public int istEsEnthalten(Urlaubzeit urlaubzeit1, Urlaubzeit urlaubzeit2) { // 返回21 前包后，返回22 后报前，返回0 不存在包含关系
        if (urlaubzeit1.getBeginn().compareTo(urlaubzeit2.getBeginn()) <= 0 // u1包含u2
            && urlaubzeit1.getEnd().compareTo(urlaubzeit2.getEnd()) >= 0) {
            return 21;
        }
        if (urlaubzeit1.getBeginn().compareTo(urlaubzeit2.getBeginn()) >= 0 // u2包含u1
            && urlaubzeit1.getEnd().compareTo(urlaubzeit2.getEnd()) <= 0) {
            return 22;
        }
        return 0;
    }

    public int istVerbunden(Urlaubzeit urlaubzeit1, Urlaubzeit urlaubzeit2) { //返回31 u1拼接u2，u1在上   返回32 u1拼接u2，u2在上
        if (urlaubzeit1.getBeginn().compareTo(urlaubzeit2.getBeginn()) <= 0) {
            if (this.dauer(urlaubzeit1.getBeginn(), urlaubzeit2.getEnd())
                <= (this.dauer(urlaubzeit1.getBeginn(), urlaubzeit1.getEnd())
                    + this.dauer(urlaubzeit2.getBeginn(), urlaubzeit2.getEnd()))) {
                return 31;
            }
        } else {
            if (this.dauer(urlaubzeit2.getBeginn(), urlaubzeit1.getEnd())
                <= (this.dauer(urlaubzeit2.getBeginn(), urlaubzeit2.getEnd())
                    + this.dauer(urlaubzeit1.getBeginn(), urlaubzeit1.getEnd()))) {
                return 32;
            }
        }
        return 0;
    }

    // 返回0 表示不进行任何操作，1表示添加该请假记录，21表示前包后，22表示后报前，31表示前拼接后前在上，32表示前拼接后后再上 ,240 表示返回一整天
    public int pruefung(Urlaubzeit urlaubzeit, ArrayList<Urlaubzeit> urlaubzeitList) {

        if (!this.istErlaubt(urlaubzeit)) { //判断希望的请假时间是否小于150或大于240
            return 0;
        }

        if (urlaubzeitList.isEmpty()) { // 当请假记录为空时，直接添加
            return 1;
        }


        boolean gibtEsGleichenTag = false;
        int optionCode = 0;


        ArrayList<Urlaubzeit> templist = new ArrayList<>();


        for (int i = 0; i < urlaubzeitList.size(); i++) { //如果新的请假记录不是和现有的记录同一天，直接添加
            if (this.istGeleichenTag(urlaubzeitList.get(i), urlaubzeit)) {
                gibtEsGleichenTag = true;
                templist.add(urlaubzeitList.get(i));
//                optionCode = this.istEsEnthalten(urlaubzeitList.get(i),urlaubzeit);
//                if(optionCode==0){
//                    optionCode = this.istVerbunden(urlaubzeitList.get(i),urlaubzeit);
//                }

            }
        }


        if (!gibtEsGleichenTag) {
            return 1;
        }

        if (templist.size() == 1) {
            optionCode = this.istEsEnthalten(templist.get(0), urlaubzeit);
            if (optionCode == 0) {
                optionCode = this.istVerbunden(templist.get(0), urlaubzeit);
            }
            if (optionCode == 0) {
                if (templist.get(0).isStart() && urlaubzeit.isEnd()) {
                    optionCode = 1;
                } else if (templist.get(0).isEnd() && urlaubzeit.isStart()) {
                    optionCode = 1;
                }

            }
        }


        if (templist.size() != 1) {
            if (urlaubzeit.getBeginn().compareTo(templist.get(0).getEnd()) <= 0 && urlaubzeit.getEnd().compareTo(templist.get(1).getBeginn()) >= 0) {
                optionCode = 240;
            }
            if (urlaubzeit.getBeginn().compareTo(templist.get(0).getEnd()) > 0 && urlaubzeit.getEnd().compareTo(templist.get(1).getBeginn()) < 0) {
                optionCode = 0;
            }


            if (urlaubzeit.getBeginn().compareTo(templist.get(0).getEnd()) <= 0
                && urlaubzeit.getEnd().compareTo(templist.get(1).getBeginn()) < 0
                && dauer(urlaubzeit.getEnd(), templist.get(1).getBeginn()) >= 90) {

                optionCode = this.istEsEnthalten(templist.get(0), urlaubzeit);
                if (optionCode == 0) {
                    optionCode = this.istVerbunden(templist.get(0), urlaubzeit);
                }
            }



            if (urlaubzeit.getBeginn().compareTo(templist.get(0).getEnd()) > 0
                && urlaubzeit.getEnd().compareTo(templist.get(1).getBeginn()) >= 0
                && dauer(templist.get(0).getEnd(),urlaubzeit.getBeginn()) >= 90) {
                optionCode = this.istEsEnthalten(templist.get(1), urlaubzeit);
                if (optionCode == 0) {
                    optionCode = this.istVerbunden(templist.get(1), urlaubzeit);
                }
            }

        }


        return optionCode;


//        else {
//            for (int i = 0; i < urlaubzeitList.size(); i++) {
//                if(urlaubzeit.isSameDay(urlaubzeitList.get(i))){
//                    if(urlaubzeitList.get(i).isStart() && !urlaubzeitList.get(i).isEnd()){
//                        if(urlaubzeit.isEnd()&& urlaubzeitList.get(i).isDauer(urlaubzeit)){
//                            urlaubzeitList.add(urlaubzeit);
//                            return true;
//                        }
//                    }else if(!urlaubzeitList.get(i).isStart() && urlaubzeitList.get(i).isEnd()){
//                        if(urlaubzeit.isStart()&& urlaubzeit.isDauer(urlaubzeitList.get(i))){
//                            urlaubzeitList.add(urlaubzeit);
//                            return true;
//                        }
//                    }
//                }
//            }
//            urlaubzeitList.add(urlaubzeit);
//            return true;
//        }
    }

}
