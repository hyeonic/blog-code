package io.github.hyeonic.algorithm.programmers.level2;

import java.util.Arrays;

public class Java42885 {

    public static void main(String[] args) {
        Java42885 java42885 = new Java42885();
        System.out.println(java42885.solution(new int[]{70, 50, 80, 50}, 100));
        System.out.println(java42885.solution(new int[]{70, 80, 50}, 100));
    }

    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int minIndex = 0;
        int maxIndex = people.length - 1;

        int count = 0;
        while (maxIndex >= minIndex) {
            if (people[maxIndex] + people[minIndex] <= limit) {
                count++;
                maxIndex--;
                minIndex++;
                continue;
            }

            count++;
            maxIndex--;
        }

        return count;
    }
}
