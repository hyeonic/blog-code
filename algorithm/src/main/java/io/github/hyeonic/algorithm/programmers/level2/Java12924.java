package io.github.hyeonic.algorithm.programmers.level2;

public class Java12924 {

    public static void main(String[] args) {
        Java12924 java12924 = new Java12924();
        System.out.println(java12924.solution(15));
    }

    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = i; j <= n; j++) {
                sum += j;
                if (sum > n) {
                    break;
                }

                if (sum == n) {
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }
}
