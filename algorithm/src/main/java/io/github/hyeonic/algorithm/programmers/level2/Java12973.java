package io.github.hyeonic.algorithm.programmers.level2;

import java.util.Deque;
import java.util.LinkedList;

public class Java12973 {

    public static void main(String[] args) {
        Java12973 java12973 = new Java12973();
        System.out.println(java12973.solution("baabaa"));
        System.out.println(java12973.solution("cdcd"));
    }

    public int solution(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
                continue;
            }

            stack.push(c);
        }

        if (stack.isEmpty()) {
            return 1;
        }

        return 0;
    }
}
