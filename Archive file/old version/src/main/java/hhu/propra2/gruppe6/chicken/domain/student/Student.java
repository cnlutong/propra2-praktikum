package hhu.propra2.gruppe6.chicken.domain.student;

import hhu.propra2.gruppe6.chicken.domain.Klausur;

import java.util.ArrayList;
import java.util.UUID;

public class Student {
    public UUID studentid;
    public String name;
    public ArrayList<Urlaubzeit> urlaubzeitList;
    public ArrayList<Klausur> klausuren;
    private Ordnung ordnung;
    private int restUrlaubzeit;
    private int summeUrlaubzeit;


    public Student(UUID studentid, String name, ArrayList<Urlaubzeit> urlaubzeitList, ArrayList<Klausur> klausuren) {
        this.studentid = studentid;
        this.name = name;
        this.urlaubzeitList = urlaubzeitList;
        this.klausuren = klausuren;
        this.restUrlaubzeit = 240;
        this.summeUrlaubzeit = 0;
        this.ordnung = new Ordnung();
    }

    private void rechenRestUrlaubzeit(){ //计算剩余的请假时间
            for (int i = 0; i < urlaubzeitList.size(); i++) {
                restUrlaubzeit = restUrlaubzeit - urlaubzeitList.get(i).urlaubzeitDauer();
                summeUrlaubzeit = summeUrlaubzeit + urlaubzeitList.get(i).urlaubzeitDauer();
            }
    }


    public Boolean urlaubzeitAnmelden(Urlaubzeit urlaubzeit){ // 添加新的请假

        this.rechenRestUrlaubzeit(); // 更新当前的rest和summe值
        if(restUrlaubzeit<=0 && summeUrlaubzeit >=0){
            return false;
        }

        if(ordnung.pruefung(urlaubzeit, urlaubzeitList)==1){
            urlaubzeitList.add(urlaubzeit);
            this.sort();
        }
        return false;
    }

    public void sort(){ //给列表排序
        this.urlaubzeitList.sort((x,y)->{
           if(x.getUrlaubDatum().compareTo(y.getUrlaubDatum())!=0){
               return x.getUrlaubDatum().compareTo(y.getUrlaubDatum());
           }else{
               return x.getBeginn().compareTo(y.getBeginn());
           }
        });
    }

}
