package io.github.hyeonic.algorithm.programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Java12981 {

    public static void main(String[] args) {
        Java12981 java12981 = new Java12981();
        System.out.println(Arrays.toString(java12981.solution(3,
                new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
        System.out.println(Arrays.toString(java12981.solution(5,
                new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure",
                        "establish", "hang", "gather", "refer", "reference", "estimate", "executive"})));
        System.out.println(
                Arrays.toString(
                        java12981.solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"})));
    }

    public int[] solution(int n, String[] words) {
        String prev = words[0];
        List<String> usedWords = new ArrayList<>();
        usedWords.add(prev);
        int now = 0;
        int turn = 0;
        for (int i = 1; i < words.length; i++) {
            String current = words[i];
            if (usedWords.contains(current) || prev.charAt(prev.length() - 1) != current.charAt(0)) {
                now = i % n + 1;
                turn = i / n + 1;
                break;
            }

            usedWords.add(current);
            prev = current;
        }

        return new int[]{now, turn};
    }
}
