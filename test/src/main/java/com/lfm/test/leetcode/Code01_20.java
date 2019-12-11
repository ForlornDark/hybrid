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

//
//    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
//    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
//    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//    示例：
//
//    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/add-two-numbers
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        ListNode node = null;
        ListNode cNode = listNode;
        int t = 0;
        int v1,v2;
        boolean more = false;
        for (; l1 != null || l2 != null || more;){
            v1 = 0;
            v2 = 0;
            more = false;
            if (l1 != null){
                v1 = l1.val;
                l1 = l1.next;
            }

            if (l2 != null){
                v2 = l2.val;
                l2 = l2.next;
            }
            if(l1 != null || l2 != null){
                more = true;
            }
            t = t + v1 +v2;
            if (t > 9){
                more = true;
            }
            cNode.val = t % 10;
            t = t / 10;
            if (more){
                node = new ListNode(0);
                cNode.next = node;
                cNode = cNode.next;
            }
        }
        return listNode;
    }
}
