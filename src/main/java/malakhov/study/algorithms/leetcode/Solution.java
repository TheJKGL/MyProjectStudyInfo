package malakhov.study.algorithms.leetcode;

class Solution {
    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {3, 2, 1}};

        System.out.println(maximumWealth(array));
    }
    public static int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int i = 0; i < accounts.length; i++) {
            int temp = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                temp += accounts[i][j];
                if (temp > max) {
                    max = temp;
                }
            }
        }
        return max;
        //1 2 3 
        //3 2 1
    }
}