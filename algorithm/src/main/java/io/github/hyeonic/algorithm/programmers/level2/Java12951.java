package io.github.hyeonic.algorithm.programmers.level2;

import java.util.Arrays;

public class Java12951 {

    public static void main(String[] args) {
        Java12951 java12951 = new Java12951();
        System.out.println(java12951.solution("3people unFollowed me"));
        System.out.println(java12951.solution("for the last week"));
        System.out.println(java12951.solution("for the             last week"));
        System.out.println(java12951.solution("for the             last week         "));
        System.out.println(java12951.solution("f"));
        System.out.println(java12951.solution("f           "));
        System.out.println(java12951.solution("3           "));
        System.out.println(java12951.solution("                   "));
    }

    public String solution(final String s) {
        String[] words = s.toLowerCase().split(" ");
        System.out.println(Arrays.toString(words));

        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            if (word.length() == 0) {
                stringBuilder.append(" ");
                continue;
            }

            stringBuilder.append(word.substring(0, 1).toUpperCase());
            stringBuilder.append(word.substring(1).toLowerCase());
            stringBuilder.append(" ");
        }

        if (s.charAt(s.length() - 1) != ' ') {
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    public String solution2(final String s) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = s.toLowerCase().split("");
        boolean flag = true;

        for (String word : words) {
            stringBuilder.append(flag ? word.toUpperCase() : word);
            flag = word.equals(" ");
        }

        return stringBuilder.toString();
    }
}
