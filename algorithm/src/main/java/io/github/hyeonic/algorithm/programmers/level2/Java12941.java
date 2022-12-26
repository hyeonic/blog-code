package io.github.hyeonic.algorithm.programmers.level2;

import java.util.Arrays;

public class Java12941 {

    public static void main(String[] args) {
        Java12941 java12941 = new Java12941();
        System.out.println(java12941.solution(new int[]{1, 4, 2}, new int[]{5, 4, 4}));
        System.out.println(java12941.solution(new int[]{1, 2}, new int[]{3, 4}));
    }

    public int solution(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            answer += a[i] * b[a.length - 1 - i];
        }

        return answer;
    }
}
