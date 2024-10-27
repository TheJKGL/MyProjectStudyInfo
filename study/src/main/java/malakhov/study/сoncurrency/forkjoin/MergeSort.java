package malakhov.study.сoncurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSort {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        int[] arr = {1,231,4,5,7,22,77,42,18,46};
        Task task = new Task(arr, "root");
        forkJoinPool.invoke(task);
        System.out.println(Arrays.toString(task.arr));
    }

    static class Task extends RecursiveAction {

        private int[] arr;
        private String name;

        public Task(int[] arr, String name) {
            this.arr = arr;
            this.name = name;
        }

        @Override
        protected void compute() {
            if (arr.length < 2) {
                return;
            } else {
                System.out.println(name);
                int mid = arr.length / 2;

                int[] left = new int[mid];
                System.arraycopy(arr, 0, left, 0, mid);

                int[] right = new int[arr.length - mid];
                System.arraycopy(arr, mid, right, 0, arr.length - mid);

                Task leftTask = new Task(left, "left");
                Task rightTask = new Task(right, "right");

                leftTask.fork();
                rightTask.compute();

                leftTask.join();
                merge(left, right);
            }
        }

        private void merge(int[] left, int[] right) {
            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i] < right[j])
                    arr[k++] = left[i++];
                else
                    arr[k++] = right[j++];
            }
            while (i < left.length) {
                arr[k++] = left[i++];
            }
            while (j < right.length) {
                arr[k++] = right[j++];
            }
        }
    }
}
