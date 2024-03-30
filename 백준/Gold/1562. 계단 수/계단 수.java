import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final long DIVISOR = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();
        long[][][] dp = new long[N][10][1024];
        // dp[i][j][k] : 비트 k에 대응되는 집합의 원소를 사용한 길이 i, 마지막 숫자가 j인 계단 수의 개수
        for (int i = 1; i < 10; i++) {
            dp[0][i][1 << i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int idx = 0; idx < 1024; idx++) {
                    int visitJ = idx | (1 << j);
                    if (j < 9) dp[i][j][visitJ] += dp[i - 1][j + 1][idx];
                    if (j > 0) dp[i][j][visitJ] += dp[i - 1][j - 1][idx];
                    dp[i][j][visitJ] %= DIVISOR;
                }
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[N - 1][i][1023];
            answer %= DIVISOR;
        }
        System.out.println(answer);
    }
}