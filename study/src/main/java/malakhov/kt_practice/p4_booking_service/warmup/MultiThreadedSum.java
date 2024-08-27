package malakhov.kt_practice.p4_booking_service.warmup;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MultiThreadedSum {

    /**
     * Calculates the sum of an array of integers using two threads.
     *
     * @param array The array of integers to sum.
     * @return The total sum of the array.
     */
    public int calculateSum(int[] array) {
        return recursiveSumOfArray(array);
    }

    private static int commonSumOfArray(int[] array) {
        return 0;
    }

    private static int streamSumOfArray(int[] array) {
        return Arrays.stream(array).sum();
    }

    private static int parallelSumOfArray(int[] array) {
        int[] left = Arrays.copyOfRange(array, 0, array.length / 2);
        int[] right = Arrays.copyOfRange(array, array.length / 2, array.length);

        CompletableFuture<Integer> leftCompletableFuture = CompletableFuture.supplyAsync(() -> Arrays.stream(left).sum());
        CompletableFuture<Integer> rightCompletableFuture = CompletableFuture.supplyAsync(() -> Arrays.stream(right).sum());

        int sum;
        try {
            sum = leftCompletableFuture.get() + rightCompletableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    private static int recursiveSumOfArray2(int[] array) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int mid = array.length / 2;
        ForkJoinTask<Integer> leftSum = forkJoinPool.submit(() -> calculateSum(array, 0, mid));
        ForkJoinTask<Integer> rightSum = forkJoinPool.submit(() -> calculateSum(array, mid, array.length));
        return leftSum.join() + rightSum.join();
    }

    private static int calculateSum(int[] array, int start, int end) {
        return Arrays.stream(array, start, end).sum();
    }

    private static int recursiveSumOfArray(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        RecursiveSumOfArray recursiveSumOfArray = new RecursiveSumOfArray("root", array, 0, array.length);

        return forkJoinPool.invoke(recursiveSumOfArray);
    }

    static class RecursiveSumOfArray extends RecursiveTask<Integer> {

        private int[] array;
        private final int start;
        private final int end;
        private String name;

        private static final int THRESHOLD = 5;

        public RecursiveSumOfArray(String name, int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
            this.name = name;
        }

        @Override
        protected Integer compute() {
            System.out.println(this.name);
            if (end - start <= THRESHOLD) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                int mid = (start + end) / 2;
                RecursiveSumOfArray leftTask = new RecursiveSumOfArray("left", array, start, mid);
                RecursiveSumOfArray rightTask = new RecursiveSumOfArray("right", array, mid, end);

                leftTask.fork();//todo ask
                int rightResult = rightTask.compute();
                int leftResult = leftTask.join();

                return leftResult + rightResult;
            }
        }
    }
}
