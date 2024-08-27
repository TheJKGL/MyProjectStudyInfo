package malakhov.study.algorithms.leetcode.kt_practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Practice2 {
    public static void main(String[] args) {
        Practice2 practice2 = new Practice2();
        List<Integer> list = practice2.generateList(10);
        System.out.println(list);

        List<Integer> result = practice2.sortUsingBubbleSort(list);
        System.out.println(result);
    }

    private List<Integer> sortUsingBubbleSort(List<Integer> list) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                int l1 = list.get(i);
                int l2 = list.get(i + 1);

                if (l2 < l1) {
                    list.set(i + 1, l1);
                    list.set(i, l2);
                    isSorted = false;
                }
            }
        }
        return list;
    }

    private List<Integer> generateList(int size) {
        List<Integer> arrayList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arrayList.add(random.nextInt(20));
        }

        return arrayList;
    }
}
