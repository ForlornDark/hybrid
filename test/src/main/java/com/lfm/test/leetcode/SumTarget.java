package com.lfm.test.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class SumTarget {

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,4};
        System.out.println(sumTarget3(nums,6));
    }

    /**
     * 有序
     */
    public static int[] sumTarget(int[] array,int target){
        int left = 0;
        int right = array.length - 1;
        while (left != right){
            if (array[left] + array[right] > target) {
                right--;
            }else if (array[left] + array[right] < target){
                left++;
            }else {
                break;
            }
        }
        if (left == right){
            return null;
        }
        return new int[]{left,right};
    }

    public static int[] sumTarget2(int[] nums,int target){
        for (int i = 0;i < nums.length - 1;i++){
            for (int j = i + 1;j < nums.length;j++){
                if (nums[i]+ nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static int[] sumTarget3(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i  = 0;i < nums.length;i++){
            int s = target - nums[i];
            if (map.containsKey(s)){
                return new int[]{map.get(s),i};
            }else {
                map.put(nums[i],i);
            }
        }
        return null;
    }

}
