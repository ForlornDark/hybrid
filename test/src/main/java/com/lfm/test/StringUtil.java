package com.lfm.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class StringUtil {


    private static String subStrIndex(String str,String deli,int count){
        char[] delis = deli.toCharArray();
        if( delis.length == 0){
            if (count < 0){
                return str.substring(str.length() + count);
            }else {
                return str.substring(0 ,count);
            }
        }
        char[] chars = str.toCharArray();
        int i = 0;
        int j;
        int index=0;
        int step= 1;
        int end = chars.length;
        int jEnd = delis.length;
        int jStart = 0;
        int k = count;
        if (count < 0){
            end = -1;
            i = chars.length -1;
            jEnd = -1;
            jStart =  delis.length - 1;
            k = -count;
            step = -1;
        }

        for (;i != end && k > 0;){
            j = jStart;
            if (chars[i] != delis[j]){
                i+=step;
                continue;
            }
            for (j+=step,i+=step;i != end && j != jEnd;j+=step,i+=step){
                if (chars[i] != delis[j]){
                    i+=step;
                    break;
                }
            }
            index = i - delis.length*step;
            if (j == jEnd){
                k--;
            }
        }
        if (k > 0){
            return str;
        }
        if (count < 0){
            return str.substring(index+1);
        }
        return str.substring(0,index);
    }

    public static String revert(String str){
        if(str == null || str.length() < 2){
            return str;
        }
        char[] chars = str.toCharArray();
        int end = chars.length - 1;
        int half = chars.length / 2;
        char c;
        for (int i = 0;i < half; i++){
            c = chars[i];
            chars[i] = chars[end - i];
            chars[end -i] = c;
        }
        return new String(chars);
    }


    public static String reverseSentence(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        for (int i = 0,j =0;j <= n;j++) {
            if (j == n || chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
        }
        reverse(chars, 0, n - 1);
        return new String(chars);
    }
    private static void reverse(char[] c, int i, int j) {
        for (;i < j;i++, j--)
            swap(c, i,j);
    }
    private static void swap(char[] c, int i, int j) {
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }

//    private static void process(char[] temp, int[] f) {
//        int n =temp.length;
//        f[0] = f[1] = 0; //边界
//        for(int i = 1; i < n; ++i) {
//            int j = f[i];
//            while(j !=0 && temp[i] != temp[j]) j = f[j]; //一旦回到1，表明窗口大小为0了，只能回到最初的字符
//            f[i + 1] = temp[i] == temp[j] ? j + 1: 0;
//        }
//    }
//
//    private  static void findStr(String str, String find) {
//        int n = str.length(), m = find.length();
//        int[] f = new int[m + 1];
//        char[] findArray = find.toCharArray();
//        process(findArray, f); //预处理得到失配表
//        int j = 0; //j表示当前模版串的待匹配位置
//        char[] strArray = str.toCharArray();
//        for(int i = 0; i < n; ++i) {
//            while(j != 0 && strArray[i] != findArray[j]) j = f[j]; //不停的转移，直到可以匹配或者走到0
//            if(strArray[i] == findArray[j]) j++; //如果相等，模版串中待匹配位置可以移一位了。
//            if(j == m) System.out.println(i - m + 1);
//        }
//    }

    public static void main(String[] args){
//        String a ="adbafdsvssks".indexOf("dsa");
//        findStr(a,"fds");
    }
}
