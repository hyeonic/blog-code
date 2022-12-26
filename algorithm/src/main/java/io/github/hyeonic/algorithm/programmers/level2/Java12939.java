package io.github.hyeonic.algorithm.programmers.level2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Java12939 {

    public static void main(String[] args) {
        Java12939 java12939 = new Java12939();
        System.out.println(java12939.solution("1 2 3 4"));
        System.out.println(java12939.solution("-1 -2 -3 -4"));
        System.out.println(java12939.solution("-1 -1"));
    }

    public String solution(String s) {
        List<Integer> numbers = Arrays.stream(s.split(" ", -1))
                .mapToInt(Integer::valueOf)
                .boxed()
                .collect(Collectors.toList());

        Integer max = Collections.max(numbers);
        Integer min = Collections.min(numbers);

        return min + " " + max;
    }

    public String solution2(String s) {
        List<Integer> numbers = Arrays.stream(s.split(" "))
                .mapToInt(Integer::valueOf)
                .boxed()
                .collect(Collectors.toList());

        int min = numbers.get(0);
        int max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            min = Math.min(min, numbers.get(i));
            max = Math.max(max, numbers.get(i));
        }

        return min + " " + max;
    }
}
