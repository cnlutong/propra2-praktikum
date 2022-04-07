package hhu.propra2.gruppe6.chicken.domain.student;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public class StudentList {

    public static HashMap<UUID, Student> studentList;

    public static void insertStudentCheckList(UUID uuid, Student student) {
        studentList.put(uuid, student);
    }

    public static void setStudentRollCall(LocalDate localDate, UUID uuid, String str) {
        studentList.get(uuid).getRollCall().insertRollCall(localDate, str);
    }

    public static String getStudentRollCall(LocalDate localDate, UUID uuid){
        return studentList.get(uuid).getRollCall().getRollCallPositon(localDate);
    }

    public static double getStudentRollCallPercentage(UUID uuid){
        return studentList.get(uuid).getRollCall().getPercentage();
    }

    public static String getStudentName(UUID uuid){
        return studentList.get(uuid).getName();
    }

    public static int getStudentGroup(UUID uuid){
        return studentList.get(uuid).getGroup();
    }


}
