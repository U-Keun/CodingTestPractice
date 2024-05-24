import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static long[][] dp;
    private static boolean[] visited;
    private static final int MOD = 1000000009;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new long[3][100001];
        visited = new boolean[100001];
        dp[0][1] = 1;
        dp[1][2] = 1;
        dp[2][3] = 1;
        visited[1] = true;
        visited[2] = true;
        int input;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            print.append(compute(input)).append("\n");
        }
        br.close();
        System.out.println(print);
    }
    private static long compute(int number) {
        for (int i = 3; i <= number; i++) {
            if (visited[i]) continue;
            dp[0][i] += dp[1][i - 1] + dp[2][i - 1];
            dp[0][i] %= MOD;
            dp[1][i] += dp[0][i - 2] + dp[2][i - 2];
            dp[1][i] %= MOD;
            dp[2][i] += dp[0][i - 3] + dp[1][i - 3];
            dp[2][i] %= MOD;
            visited[i] = true;
        }
        return (dp[0][number] + dp[1][number] + dp[2][number]) % MOD;
    }
}