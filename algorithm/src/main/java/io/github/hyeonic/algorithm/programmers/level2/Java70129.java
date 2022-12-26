package io.github.hyeonic.algorithm.programmers.level2;

import java.util.Arrays;

public class Java70129 {

    public static void main(String[] args) {
        Java70129 java70129 = new Java70129();
        System.out.println(Arrays.toString(java70129.solution("110010101001")));
        System.out.println(Arrays.toString(java70129.solution("01110")));
        System.out.println(Arrays.toString(java70129.solution("1111111")));
    }

    public int[] solution(final String s) {
        int cycleCount = 0;
        int extractCount = 0;

        String binaryNumber = s;
        while (true) {
            extractCount += extract(binaryNumber);
            cycleCount++;

            String replaceNumber = binaryNumber.replaceAll("0", "");
            if (isOne(replaceNumber)) {
                break;
            }

            binaryNumber = translate(replaceNumber);
        }

        return new int[]{cycleCount, extractCount};
    }

    private int extract(final String binaryNumber) {
        int count = 0;
        for (char c : binaryNumber.toCharArray()) {
            if (c == '0') {
                count++;
            }
        }
        return count;
    }

    private boolean isOne(final String binaryNumber) {
        if (binaryNumber.equals("1")) {
            return true;
        }
        return false;
    }

    private String translate(final String binaryNumber) {
        int number = binaryNumber.length();
        return Integer.toBinaryString(number);
    }
}
