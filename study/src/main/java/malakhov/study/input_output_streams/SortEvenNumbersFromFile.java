package malakhov.study.input_output_streams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SortEvenNumbersFromFile {

    private static final String PACKAGE_PATH = "/Users/yema/Desktop/Programming/Projects/project-study-info/src/main/java/malakhov/study/input_output_streams/";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter file name:");

        String fileName = reader.readLine();
        FileInputStream fileInputStream = new FileInputStream(PACKAGE_PATH + fileName);

        Scanner scanner = new Scanner(fileInputStream);

        ArrayList<Integer> integers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            int s = scanner.nextInt();
            if (s % 2 == 0) {
                integers.add(s);
            }
        }

        Collections.sort(integers);
        for (Integer i : integers) {
            System.out.println(i);
        }

        scanner.close();
        reader.close();
    }
}
