package com.lfm.test.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 常见排序算法
 */
public class Sort{

    public static void main(String[] args){
        int[] ints = new Random().ints(100, 1, 100).toArray();
        Arrays.stream(ints).forEach(value -> System.out.print(value+","));
        quickSort(ints,0,ints.length-1);
        System.out.println("排序后：");
        Arrays.stream(ints).forEach(value -> System.out.print(value+","));
    }


    public  static void selectSort(int[] list){
        if (list == null || list.length == 1){
            return;
        }
        int index;
        int t;
        for (int i =0;i < list.length - 1;i++){
            index = i;
            for (int j = i + 1;j < list.length;j++){
                if (list[j] < list[index]){
                    index = j;
                }
            }
            if (index != i){
                t = list[index];
                list[index] = list[i];
                list[i] = t;
            }
        }
    }

    public  static  void bubSort(int[]list){
        if (list == null || list.length == 1){
            return;
        }
        int c;
        for (int i = list.length -1;i > 1 ;i--){
            for (int j = 0;j < i;j++){
                if (list[j] > list[j+1]){
                    c = list[j];
                    list[j] = list[j+1];
                    list[j+1] = c;
                }
            }
        }
    }
    public  static void quickSort(int[] list,int left,int right){

        if (left >= right){
            return;
        }
        int i = left;
        int j = right;
        int min = list[left];
        while (i < j){
            while ( i < j && min <= list[j]){
                j--;
            }
            list[i] = list[j];
            while (i < j && min >= list[i] ){
                i++;
            }
            list[j] = list[i];
        }
        list[i] = min;
        quickSort(list,left,i-1);
        quickSort(list,i+1,right);

    }


    public static void insertionSort(int[] list){
        if (list == null || list.length == 1){
            return;
        }
        int t;
        int j;
        for (int i = 1;i < list.length;i++){
            t = list[i];
            for (j = i-1;j >= 0 && t < list[j];j--){
                list[j+1] = list[j];
            }
            list[j+1] = t;
        }
    }

}
