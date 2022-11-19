package io.github.hyeonic.algorithm.codility.lesson6.maxproductofthree;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{-3, 1, 2, -2, 5, 6}));
        System.out.println(solution.solution(new int[]{-5, 5, -5, 4}));
    }

    public int solution(int[] a) {
        Arrays.sort(a);

        int n = a.length;
        int first = a[0] * a[1];
        int second = a[n - 3] * a[n - 2];
        int last = a[n - 1];

        if (last < 0) {
            return Math.min(first, second) * last;
        }

        return Math.max(first, second) * last;
    }
}
