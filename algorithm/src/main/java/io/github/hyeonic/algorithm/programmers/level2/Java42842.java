package io.github.hyeonic.algorithm.programmers.level2;

import java.util.Arrays;

public class Java42842 {

    public static void main(String[] args) {
        Java42842 java42842 = new Java42842();
        System.out.println(Arrays.toString(java42842.solution(10, 2)));
        System.out.println(Arrays.toString(java42842.solution(8, 1)));
        System.out.println(Arrays.toString(java42842.solution(24, 24)));
    }

    public int[] solution(int brown, int yellow) {
        int row = 0; // 가로
        int col = 0; // 세로

        for (int i = 1; i <= (brown - 4) / 2; i++) {
            row = i;
            col = ((brown - 4) / 2) - row;

            if (row >= col && row * col == yellow) {
                break;
            }
        }

        return new int[]{row + 2, col + 2};
    }
}
