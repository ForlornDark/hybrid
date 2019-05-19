package com.lfm.test;

import java.util.Collections;
import java.util.LinkedList;

class Main
{
    public static void main (String [] args)
    {
        System.out.println(fibonacci(7));
        LinkedList linkedList = new LinkedList();
    }

    public static int fibonacci(int n){
        int sum;
        if (n < 0){
            throw new IllegalArgumentException("位数n不能小于0");
        }
        if (n == 0 ){
           sum = 0;
        }else if (n == 1){
            sum = 1;
        }else {
            sum = fibonacci(n-1) + fibonacci(n-2);
        }
        return sum;
    }
}