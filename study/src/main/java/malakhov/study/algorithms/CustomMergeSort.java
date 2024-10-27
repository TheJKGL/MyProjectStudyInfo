package malakhov.study.algorithms;

import java.util.Arrays;

public class CustomMergeSort {
    public static void main(String[] args) {
        int[] arr = {5, 1, 8, 2, 4, 11, -5, 15, 6, 18};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr) {
        if (arr.length == 1) return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0; int j = 0; int inx = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[inx++] = left[i++];
            } else {
                arr[inx++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[inx++] = left[i++];
        }
        while (j < right.length) {
            arr[inx++] = right[j++];
        }
    }
}
