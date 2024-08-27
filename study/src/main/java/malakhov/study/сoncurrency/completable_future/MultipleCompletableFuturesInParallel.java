package malakhov.study.сoncurrency.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultipleCompletableFuturesInParallel {


    //When we run this code, it will start all three futures in parallel and then wait for them to complete
    // before printing the results. The order of the results may vary depending on which future completes first,
    // but all three results will be printed to the console once they are all available.
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            // Some long-running operation
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Result 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            // Some long-running operation
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Result 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            // Some long-running operation
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Result 3";
        });


        long start = System.currentTimeMillis();

        //CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);
        //
        //allFutures.thenRun(() -> {
        //    // All futures completed
        //    String result1 = future1.join();
        //    String result2 = future2.join();
        //    String result3 = future3.join();
        //    System.out.println(result1 + ", " + result2 + ", " + result3);
        //});

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));
        long end = System.currentTimeMillis();
        System.out.println(combined);
        System.out.println("Elapsed time: " + (end - start));
    }
}
