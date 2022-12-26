package io.github.hyeonic.algorithm.programmers.level2;

public class Java12945 {

    public static void main(String[] args) {
        Java12945 java12945 = new Java12945();
        System.out.println(java12945.solution(3));
        System.out.println(java12945.solution(5));
    }

    public int solution(int n) {
        int[] dp = new int[100_001];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= 100_000; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }

        return dp[n];
    }

    public int solution1(int n) {
        return fibonacci(n);
    }

    public int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
