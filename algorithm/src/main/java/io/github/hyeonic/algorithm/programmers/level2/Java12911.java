package io.github.hyeonic.algorithm.programmers.level2;

public class Java12911 {

    public static void main(String[] args) {
        Java12911 java12911 = new Java12911();
        System.out.println(java12911.solution(78));
        System.out.println(java12911.solution(15));
    }

    public int solution(int n) {
        int oneCount = extract(n);

        int result = 0;
        for (int i = n + 1; i <= 1_000_000; i++) {
            if (oneCount == extract(i)) {
                result = i;
                break;
            }
        }

        return result;
    }

    private int extract(int n) {
        int count = 0;
        String binaryNumber = Integer.toBinaryString(n);
        for (char c : binaryNumber.toCharArray()) {
            if (c == '1') {
                count++;
            }
        }

        return count;
    }

    public int solution1(int n) {
        int oneCount = Integer.bitCount(n);

        int result = 0;
        for (int i = n + 1; i <= 1_000_000; i++) {
            if (oneCount == Integer.bitCount(i)) {
                result = i;
                break;
            }
        }

        return result;
    }
}
