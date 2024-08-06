package malakhov.study.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

//Example 1:
//
//Input: s = "abccccdd"
//Output: 7
//Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.

//Example 2:
//
//Input: s = "a"
//Output: 1
//Explanation: The longest palindrome that can be built is "a", whose length is 1.
public class TheLongestPalindrome {
    public static void main(String[] args) {
        //bananas
        //anbna
        System.out.println(longestPalindrome("cabcacdd"));
    }

    public static int longestPalindrome(String s) {
        String[] temp = s.toLowerCase().split("");
        Map<String, Integer> map = new HashMap<>();

        if (s.length() == 1) {
            return 1;
        }

        for(String let : temp) {
            if (map.containsKey(let)) {
                int counter = map.get(let);
                map.put(let, ++counter);
            } else {
                map.put(let, 1);
            }
        }
        int result = 0;
        boolean hasOddFrequency = false;
        for(int freq : map.values()) {
            if (freq % 2 == 0) {
                result += freq;
            }
            if (freq % 2 == 1) {
                result += freq - 1;
                hasOddFrequency = true;
            }
        }

        return hasOddFrequency ? result + 1 : result;
    }
}
