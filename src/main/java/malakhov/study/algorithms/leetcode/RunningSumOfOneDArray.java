package malakhov.study.algorithms.leetcode;

import java.util.Arrays;

//Input: nums = [1,2,3,4]
//Output: [1,3,6,10]
//Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
class RunningSumOfOneDArray {
    public static void main(String[] args) {
        RunningSumOfOneDArray.runningSum2(new int[]{1,2,3,4,});
    }
    public static int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        int counter = 0;

        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int j = 0; j <= i; j++) {
                temp += nums[j];
            }
            result[i] = temp;
        }
        return result;
    }

    public static int[] runningSum2(int[] nums) {
        for(int i=1;i<nums.length;i++){
            nums[i]=nums[i-1]+nums[i];

        }
        return nums;
    }
}