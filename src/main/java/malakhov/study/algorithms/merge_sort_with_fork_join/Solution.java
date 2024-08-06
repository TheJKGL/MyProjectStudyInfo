package malakhov.study.algorithms.merge_sort_with_fork_join;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {2, 5, 6, 7, 2, 55, 22, 11, 9, 3, 1, 0, 10, 34, 23};
        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        MergeSort mergeSort = new MergeSort(arr);
        pool.invoke(mergeSort);
        long finish = System.currentTimeMillis();
        System.out.println("Time Taken: " + (finish - start));
        System.out.println(Arrays.toString(arr));
    }
}
