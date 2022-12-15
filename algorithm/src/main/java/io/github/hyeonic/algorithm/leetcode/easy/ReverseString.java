package io.github.hyeonic.algorithm.leetcode.easy;

import java.util.Arrays;

public class ReverseString {

    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        reverseString.reverseString(new char[]{'h','e','l','l','o'});
    }

    public void reverseString(char[] s) {
        char[] temp = Arrays.copyOf(s, s.length);
        for (int i = 0; i < s.length; i++) {
            s[i] = temp[s.length - 1 - i];
        }

        System.out.println(Arrays.toString(s));
    }
}
