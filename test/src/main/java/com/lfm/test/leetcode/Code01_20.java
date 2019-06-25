package com.lfm.test.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Code01_20 {





    /**
     给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

     示例 1:

     输入: 123
     输出: 321
     示例 2:

     输入: -123
     输出: -321
     示例 3:

     输入: 120
     输出: 21
     注意:

     假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     */
    public static int reverse(int x){
        int j = 0;
        int d = x < 0 ? Integer.MIN_VALUE / 10:Integer.MAX_VALUE / 10;
//        int e = x < 0 ? Integer.MIN_VALUE % 10 : Integer.MAX_VALUE % 10;
        for (;x != 0;){
            j = j * 10 + x % 10;
            x /= 10;
            if ((x < 0 && j < d) || (x > 0 && j > d)){
                return 0;
            }

        }
        return j;
    }

    /**
     * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
     *
     * 请你重新排列这些条形码，使其中两个相邻的条形码 不能 相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[1,1,1,2,2,2]
     * 输出：[2,1,2,1,2,1]
     * 示例 2：
     *
     * 输入：[1,1,1,1,2,2,3,3]
     * 输出：[1,3,1,3,2,1,2,1]
     */

    public static int[] rearrangeBarcodes(int[] barcodes) {
        if (barcodes == null||barcodes.length < 2){
            return barcodes;
        }

        int t;
        for (int i = 1;i < barcodes.length;i++){
            if (barcodes[i-1] == barcodes[i]){
                swap(barcodes,0,i-1,i);
                swap(barcodes,i + 1,barcodes.length,i);
            }

        }

        return barcodes;
    }

    private static void swap(int[] barcodes,int left,int right,int i2){
        int t;
        for (;left < right;left++){
            boolean swap;
            swap = barcodes[left] != barcodes[i2];
            if (swap && left != 0){
               swap = barcodes[left-1]!= barcodes[i2];
            }

            if (swap && left != right - 1){
                swap = barcodes[left + 1] != barcodes[i2];
            }
            swap = swap && barcodes[left] != barcodes[i2-1] && barcodes[left] == barcodes[i2+1];
            if (swap){
                t = barcodes[i2];
                barcodes[left] = barcodes[i2];
                barcodes[i2]=t;
                break;
            }

        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,2,2,1,5};
        rearrangeBarcodes(arr);
        for (int a:arr){
            System.out.println(a+",");
        }
    }
}
