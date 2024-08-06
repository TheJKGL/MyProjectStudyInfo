package malakhov.study;

import java.util.ArrayList;
import java.util.List;

public class InterviewTestAssignment {

    public static void main(String[] args) {
        System.out.println(function(100, 5, 145));
    }

    private List<Integer> storage = new ArrayList<>();

    public void countNumberOfPack(int val) {
        int buck = storage.get(val);
        buck += 1;
        storage.set(val, buck);
    }

    //n - 100 секторов
    //s - 5 сектор
    //m - 145 секторов мы повернули
    public static int function(int n, int s, int m) {
        return m % n + s;
    }
}
