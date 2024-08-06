package malakhov.study.сoncurrency.examples_with_future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class Solution {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Solution.exampleWithOneThread();
        Solution.exampleWithTwoThreads();
        Solution.exampleWithForkJoinTask();
    }

    private static void exampleWithOneThread() throws InterruptedException, ExecutionException {
        Future<Integer> future = new SquareCalculator().calculate(10);

        while(!future.isDone()) {
            System.out.println("Calculating...");
            Thread.sleep(300);
        }

        Integer result = future.get();
        System.out.println(result);
    }

    private static void exampleWithTwoThreads() throws ExecutionException, InterruptedException {
        SquareCalculator squareCalculator = new SquareCalculator();

        Future<Integer> future1 = squareCalculator.calculate(10);
        Future<Integer> future2 = squareCalculator.calculate(100);

        while (!(future1.isDone() && future2.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s",
                            future1.isDone() ? "done" : "not done",
                            future2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Integer result1 = future1.get();
        Integer result2 = future2.get();

        System.out.println(result1 + " and " + result2);

        squareCalculator.shutdown();
    }

    private static void exampleWithForkJoinTask() {
        class FactorialSquareCalculator extends RecursiveTask<Integer> {

            private Integer n;

            public FactorialSquareCalculator(Integer n) {
                this.n = n;
            }

            @Override
            protected Integer compute() {
                if (n <= 1) {
                    return n;
                }

                FactorialSquareCalculator calculator = new FactorialSquareCalculator(n - 1);

                calculator.fork();

                return n * n + calculator.join();
            }
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        FactorialSquareCalculator calculator = new FactorialSquareCalculator(10);

        forkJoinPool.execute(calculator);
    }
}
