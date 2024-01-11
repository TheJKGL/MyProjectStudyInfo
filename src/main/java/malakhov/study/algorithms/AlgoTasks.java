package malakhov.study.algorithms;

public class AlgoTasks {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 50, 5, 100, 5};
        int[] arrayAscending = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(AlgoTasks.sumOfElements(array));
        System.out.println(AlgoTasks.maxValueAndIndex(array));
        System.out.println(AlgoTasks.minValueAndIndex(array));
        System.out.println(AlgoTasks.countNumberOf(5, array));
        System.out.println(AlgoTasks.isArraySortedAscending(arrayAscending));
    }

    static int sumOfElements(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    static String maxValueAndIndex(int[] array) {
        int value = array[0];
        int index = -1;
        for (int i = 1; i < array.length; i++) {
            if (value < array[i]) {
                value = array[i];
                index = i;
            }
        }
        if (index == -1) {
            index = 0;
        }
        return "Value: " + value + " Index: " + index;
    }

    static String minValueAndIndex(int[] array) {
        int value = array[0];
        int index = -1;
        for (int i = 1; i < array.length; i++) {
            if (value > array[i]) {
                value = array[i];
                index = i;
            }
        }
        if (index == -1) {
            index = 0;
        }
        return "Value: " + value + " Index: " + index;
    }

    //Посчитать количество элементов равных заданному
    static int countNumberOf(int k, int[] array) {
        int amount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) {
                amount++;
            }
        }
        return amount;
    }

    //Проверить массив на упорядоченность элементов по возрастанию
    static boolean isArraySortedAscending(int[] array) {
        boolean result = false;
        for (int i = 0; i < array.length - 1; i++) {
            result = array[i] < array[i + 1];
        }
        return result;
    }

    //Давай найдем наибольший общий делитель (НОД).
    private static void getNod(int a, int b) {
        if (a == b) {
            System.out.println(a);
        } else {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
            getNod(a, b);
        }
    }

    private static int getNOD(int first, int second) {
        if (first < 1 || second < 1) {
            throw new IllegalArgumentException();
        }

        while (first != second) {
            if (first > second) {
                first -= second;
            }
            if (second > first) {
                second -= first;
            }
        }
        return first;
    }
}
