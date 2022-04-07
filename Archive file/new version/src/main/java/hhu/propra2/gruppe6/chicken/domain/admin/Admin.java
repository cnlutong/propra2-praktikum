package hhu.propra2.gruppe6.chicken.domain.admin;

import hhu.propra2.gruppe6.chicken.domain.student.StudentList;

import java.util.HashMap;
import java.util.UUID;

public class Admin {

    private String name;
    private UUID uuid;

    public Admin(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public double getStudentRollCallPercentage(UUID uuid){
        return StudentList.getStudentRollCallPercentage(uuid);
    }
    
    public HashMap<UUID, Double> getAllStudentRollCallPercentage(){
        HashMap<UUID, Double> ruselt = new HashMap<>();
        for (UUID uuid: StudentList.studentList.keySet()) {
            ruselt.put(uuid, StudentList.getStudentRollCallPercentage(uuid));
        }
        return ruselt;
    }
}
