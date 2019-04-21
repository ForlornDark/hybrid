package com.lfm.test;

import java.util.Arrays;

public class Hilbert {

    private static long  hi(int n, int x, int y) {
        if (n == 0) return 1;
        int m = 1 << (n - 1);
        if (x <= m && y <= m) {
            return hi(n - 1, y, x);
        }
        else if (x > m && y <= m) {
            return 3L * m * m + hi(n - 1, m + 1 - y, m * 2 - x + 1);
        }
        else if (x <= m && y > m) {
            return (long) m * m + hi(n - 1, x, y - m);
        }
        //if (x > m && y > m)
        else {
            return 2L * m * m + hi(n - 1, x - m, y - m);
        }
    }

    public static void main(String[] args){
        System.out.println(System.currentTimeMillis());
        System.out.println(hi(1,3,3));
        System.out.println(System.currentTimeMillis());
        System.out.println(System.getProperty("file.encoding"));

        Integer[] as = new Integer[]{1,234,4325,1,1,34,4};
//        Arrays.sort(as);

        Arrays.sort(as,(a,b)-> b-a);
        for (int i :as){
            System.out.println(i);
        }
    }
}
