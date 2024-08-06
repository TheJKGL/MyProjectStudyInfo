package malakhov.study.сoncurrency.examples_with_future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculator {

    //Option #1
    //private ExecutorService executor = Executors.newSingleThreadExecutor();

    //Option #2
    private ExecutorService executor = Executors.newFixedThreadPool(2);
    
    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}