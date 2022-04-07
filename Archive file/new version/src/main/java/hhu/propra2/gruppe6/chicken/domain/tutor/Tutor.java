package hhu.propra2.gruppe6.chicken.domain.tutor;

import hhu.propra2.gruppe6.chicken.domain.student.StudentList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Tutor {
    private String name;
    private UUID uuid;
    private ArrayList<UUID> myStudent; //我掌管的学生

    public Tutor(String name, UUID uuid, ArrayList<UUID> myStudent) {
        this.name = name;
        this.uuid = uuid;
        this.myStudent = myStudent;
    }

    private String setStudentPosition(LocalDate localDate, UUID uuid, String str) {
        if(StudentList.getStudentRollCall(localDate,uuid).equals("null")){
            StudentList.setStudentRollCall(localDate, uuid, str);
            return "OK，记录他缺勤了";
        }
        return "失败，这家伙早有准备, 这天他"+StudentList.getStudentRollCall(localDate,uuid)+"了";
    }

    private void insertStudentToMyList(UUID uuid){
        myStudent.add(uuid);
    }

    public ArrayList<UUID> getMyStudent() {
        return myStudent;
    }
}
