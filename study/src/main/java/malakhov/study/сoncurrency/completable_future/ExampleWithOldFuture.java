package malakhov.study.сoncurrency.completable_future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ExampleWithOldFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return "Result 1";
        });

        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return "Result 2";
        });

        FutureTask<String> futureTask3 = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return "Result 3";
        });

        List<FutureTask<String>> futureTasks = new ArrayList<>();
        futureTasks.add(futureTask1);
        futureTasks.add(futureTask2);
        futureTasks.add(futureTask3);

        //User 1 or 3 thread;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        long start = System.currentTimeMillis();
        for(FutureTask<String> task : futureTasks) {
            executorService.submit(task);
        }
        StringBuilder result = new StringBuilder();
        for(FutureTask<String> task : futureTasks) {
            result.append(task.get());
        }
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("Elapsed time: " + (end - start));
    }
}
