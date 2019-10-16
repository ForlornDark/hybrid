package com.lfm.test.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Code41_60 {

    public static void main(String[] args) {
        Code41_60 code41_60 = new Code41_60();
        List<List<String>> list = code41_60.solveNQueens(13);
        System.out.println("num = " + code41_60.num);
        list.forEach(e->{
            System.out.println("re-----");
            e.forEach(System.out::println);
        });
    }
    /**
     给定一个 n × n 的二维矩阵表示一个图像。

     将图像顺时针旋转 90 度。

     说明：

     你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

     示例 1:

     给定 matrix =
     [
     [1,2,3],
     [4,5,6],
     [7,8,9]
     ],

     原地旋转输入矩阵，使其变为:
     [
     [7,4,1],
     [8,5,2],
     [9,6,3]
     ]
     示例 2:

     给定 matrix =
     [
     [ 5, 1, 9,11],
     [ 2, 4, 8,10],
     [13, 3, 6, 7],
     [15,14,12,16]
     ],

     原地旋转输入矩阵，使其变为:
     [
     [15,13, 2, 5],
     [14, 3, 4, 1],
     [12, 6, 8, 9],
     [16, 7,10,11]
     ]

     [0,0],[3,0],[3,3],[0,3]
     [i,j],[j,len-i],[len-i,len-j],[len-j,i]

     */
    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return;
        }
        int len = matrix.length-1;
        int t;
        for (int i = 0;i < len ;i++){
            for (int j = i ;j < len-i;j++){
              t = matrix[i][j];
              matrix[i][j] = matrix[len-j][i];
              matrix[len-j][i] = matrix[len-i][len-j];
              matrix[len-i][len-j]= matrix[j][len-i];
              matrix[j][len-i]=t;
            }
        }
    }

    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     * @param n n
     * @return ss
     */
    private List<List<String>> list = new ArrayList<>();
    private char[][] ques;
    private int num;
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0){
            return list;
        }
        ques = new char[n][n];
        init(ques);
        back(0);
        return list;
    }

    private void back(int d){
        int p = 0;
        for (;p < ques.length && d < ques.length;p++){
            if (checkQueens(d,p)){
                ques[d][p] = 'Q';
                if (d == ques.length - 1){
                    list.add(putString(ques));
                    num++;
                }else {
                    back(d+1);
                }
                ques[d][p] = '.';
            }
        }

    }

    private boolean checkQueens(int d,int p){
        int i,j;

        for (i = d - 1,j= p - 1;i >= 0 && j >= 0;i--,j--){
            if (ques[i][j] == 'Q'){
                return false;
            }
        }


        for (i = d - 1,j= p + 1;i >= 0 && j <ques.length;i--,j++){
            if (ques[i][j] == 'Q'){
                return false;
            }
        }

        for (i = 0;i < d;i++){
            if (ques[i][p] == 'Q') {
                return false;
            }
        }
      
        return true;
    }

    private List<String> putString(char[][] ques){
        List<String> list = new ArrayList<>(ques.length);
        for (int i = 0; i < ques.length; i++) {
            list.add(new String(ques[i]));
        }
        return list;
    }

    private void init(char[][] ques){
        for (int i = 0;i<ques.length;i++){
            for (int j = 0 ;j < ques.length;j++){
                ques[i][j] = '.';
            }
        }
    }
}
