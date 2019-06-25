package com.lfm.test.leetcode;

public class Code141_160 {
    public static void main(String[] args) {
        System.out.println(reverse("  the  sky is blue "));
    }
    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     *
     *
     *
     * 示例 1：
     *
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     * 示例 2：
     *
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 示例 3：
     *
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     *
     * 说明：
     *
     * 无空格字符构成一个单词。
     * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */

    public static String reverse(String s){
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        int space  = 0;
        int word = 0;
        int i = chars.length - 1;
        for (;i >= 0;i--){
            if (chars[i] == ' '){
                space++;
                if (word > 0){
                    save(chars, builder, i+1, i + word);
                    builder.append(' ');
                    word = 0;
                    space = 0;
                }
            }else {
                word++;
            }
        }
        if (word > 0){
            save(chars,builder,i+1,i+word);
        }
        int length = builder.length();
        if (length > 1 && builder.charAt(length - 1) == ' '){
            builder.deleteCharAt(builder.length()-1);
        }
        return builder.toString();
    }

    private static void save(char[] chars,StringBuilder builder,int i,int j){
        if (i >= 0 && i < chars.length && j < chars.length){
            for (;i<=j;i++){
                builder.append(chars[i]);
            }
        }

    }
}
