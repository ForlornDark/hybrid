package com.lfm.test;

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

    public static void main(String[] args){
        System.out.println(revert("123456"));
    }
}
