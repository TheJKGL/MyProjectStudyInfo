package malakhov.study.сoncurrency.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExamples {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Example1
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Order #1024");
        completableFuture.thenAccept(System.out::println);
    }
}
