package malakhov.study.algorithms.leetcode.assignment2;

import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        //int val = Integer.parseInt("9999999991");
        System.out.println(Integer.MAX_VALUE);
        System.out.println("9999999991");
        int number = 807; // = and int
        LinkedList<Integer> stack = new LinkedList<Integer>();
        while (number > 0) {
            stack.push( number % 10 );
            number = number / 10;
        }
        System.out.println(stack);
    }
}
