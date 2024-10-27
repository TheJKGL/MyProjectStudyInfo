package malakhov.study.algorithms;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    //Ввести с консоли имя файла. Найти байт или байты с максимальным количеством повторов.
    //Вывести их на экран через пробел. Закрыть поток ввода-вывода.
    private static void theMostFrequentBytes1() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        FileInputStream fileInputStream = new FileInputStream(fileName);

        Map<Integer, Integer> countMap = new HashMap<>();
        while (fileInputStream.available() > 0) {
            int readByte = fileInputStream.read();

            if (countMap.containsKey(readByte)) {
                int count = countMap.get(readByte);
                countMap.put(readByte, ++count);
            } else {
                countMap.put(readByte, 1);
            }
        }

        int maxValue = Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getValue();
        List<Integer> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == maxValue) {
                resultList.add(entry.getKey());
            }
        }

        for (Integer integer : resultList) {
            System.out.print(integer + " ");
        }

        fileInputStream.close();
        reader.close();
    }

    private static void theMostFrequentBytes2() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        int[] byteCountArray = new int[256];
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            while (fileInputStream.available() > 0) {
                byteCountArray[fileInputStream.read()] += 1;
            }
        }
        int maxCount = 0;
        for (int byteCount : byteCountArray) {
            if (byteCount > maxCount) maxCount = byteCount;
        }
        ArrayList<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < byteCountArray.length; i++) {
            if (byteCountArray[i] == maxCount) resultList.add(i);
        }
        for (Integer resultItem : resultList) System.out.print(resultItem + " ");
    }

    public static int sumOfUnique(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            Integer value = count.get(num);
            if (value == null) {
                count.put(num, 1);
            } else {
                count.put(num, ++value);
            }
        }

        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                sum += entry.getKey();
            }
        }
        return sum;
    }

    public <T> Set<T> findNonUniqueElements(List<T> inputList) {
        Set<T> elements = new HashSet<>();
        Set<T> duplicates = new HashSet<>();

        for (T element : inputList) {
            if (!elements.add(element)) {
                duplicates.add(element);
            }
        }

        return duplicates;
    }
}
