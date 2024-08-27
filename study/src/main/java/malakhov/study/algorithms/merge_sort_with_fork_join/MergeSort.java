package malakhov.study.algorithms.merge_sort_with_fork_join;

import java.util.concurrent.RecursiveAction;

public class MergeSort extends RecursiveAction {
    int[] arr;
    int length;

    public MergeSort(int[] arr) {
        this.arr = arr;
        this.length = arr.length;
    }

    @Override
    protected void compute() {
        if (length == 1) {
            return;
        }
        int mid = length / 2;
        int[] l = new int[mid];
        int[] r = new int[length - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }
        for (int i = mid; i < length; i++) {
            r[i - mid] = arr[i];
        }
        MergeSort mergeSortLeft = new MergeSort(l);
        MergeSort mergeSortRight = new MergeSort(r);
        mergeSortLeft.fork();
        //mergeSortRight.fork();

        mergeSortRight.compute();

        mergeSortLeft.join();
        //mergeSortRight.join();

        merge(l, r, mid, length - mid);
    }

    public void merge(int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                arr[k++] = l[i++];
            } else {
                arr[k++] = r[j++];
            }
        }
        while (i < left) {
            arr[k++] = l[i++];
        }
        while (j < right) {
            arr[k++] = r[j++];
        }
    }
}
