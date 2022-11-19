package io.github.hyeonic.algorithm.codility.lesson6.distinct;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{2, 1, 1, 2, 3, 1}));
    }

    public int solution(int[] a) {
        Set<Integer> numbers = Arrays.stream(a)
                .boxed()
                .collect(Collectors.toSet());

        return numbers.size();
    }
}
