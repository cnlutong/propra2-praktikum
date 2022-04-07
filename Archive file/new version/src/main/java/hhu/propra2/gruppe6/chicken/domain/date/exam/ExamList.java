package hhu.propra2.gruppe6.chicken.domain.date.exam;

import java.util.ArrayList;

public class ExamList {
    private ArrayList<Exam> examList;

    public ExamList() {
        this.examList = new ArrayList<>();
    }

    public void add(Exam exam) {
        examList.add(exam);
    }

    public ArrayList<Exam> getExamList() {
        return examList;
    }

}
