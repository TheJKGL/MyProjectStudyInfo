package malakhov.study.сoncurrency.examples_with_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFutureIsDone {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "Hello World!";
        };
        Future<String> result = executorService.submit(callable);
        result.get();

        if (result.isDone()) {
            System.out.println("Is Done");
        } else {
            System.out.println("Task is still running or hasn't started");
        }
    }
}
