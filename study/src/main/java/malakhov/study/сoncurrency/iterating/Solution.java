package malakhov.study.сoncurrency.iterating;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>(integers);
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(9, true, integers);

        Thread thread = new Thread(() -> {
            Iterator<Integer> iterator = arrayBlockingQueue.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        thread.start();
        Thread.sleep(300);
        arrayBlockingQueue.add(9);
        System.out.println("added");
    }
}
