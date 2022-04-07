package hhu.propra2.gruppe6.chicken.domain.date;

import java.util.ArrayList;

public class Day {
    ArrayList<Block> oneday;

    public Day() {
        oneday = createoneday();
    }

    private ArrayList<Block> createoneday() {
        int sum = 16; //一天共有18个15分钟
        ArrayList<Block> oneday = new ArrayList<>();
        for (int i = 0; i < sum; i++) {
            oneday.add(new Block("white"));
        }
        return oneday;
    }

    public ArrayList<Block> deepCopy() {
        ArrayList<Block> newone = createoneday();
        for (int i = 0; i < newone.size(); i++) {
            newone.get(i).setColor(oneday.get(i).getColor());
        }
        return newone;
    }

    public ArrayList<Block> getOneday() {
        return oneday;
    }

    public void setOneday(ArrayList<Block> oneday) {
        this.oneday = oneday;
    }
}
