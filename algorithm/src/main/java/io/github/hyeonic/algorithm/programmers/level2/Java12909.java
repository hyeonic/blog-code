package io.github.hyeonic.algorithm.programmers.level2;

import java.util.Deque;
import java.util.LinkedList;

public class Java12909 {

    public static void main(String[] args) {
        Java12909 java12909 = new Java12909();
        System.out.println(java12909.solution("()()"));
        System.out.println(java12909.solution("(())()"));
        System.out.println(java12909.solution(")()("));
        System.out.println(java12909.solution("(()("));
    }

    boolean solution(final String s) {
        char[] brackets = s.toCharArray();

        Deque<Character> stack = new LinkedList<>();
        for (char bracket : brackets) {
            if (bracket == '(') {
                stack.push(bracket);
                continue;
            }

            if (bracket == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
                continue;
            }

            return false;
        }

        if (stack.isEmpty()) {
            return true;
        }

        return false;
    }

    boolean solution2(final String s) {
        char[] brackets = s.toCharArray();

        int count = 0;
        for (char bracket : brackets) {
            if (bracket == '(') {
                count++;
            }

            if (bracket == ')') {
                count--;
            }

            if (count < 0) {
                break;
            }
        }

        if (count == 0) {
            return true;
        }

        return false;
    }
}
