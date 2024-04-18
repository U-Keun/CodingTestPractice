import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long MOD = 1_000_000_009;
    public static void main(String[] args) throws IOException {
        long[][] dp = new long[1001][1001];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        for (int i = 3; i <= 1000; i++) {
            for (int j = 2; j <= i; j++) {
                dp[i][j] = dp[i - 3][j - 1] + dp[i - 2][j - 1] + dp[i - 1][j - 1];
                dp[i][j] %= MOD;
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
            long answer = 0;
            for (int j = 1; j <= m; j++) {
                answer += dp[n][j];
            }
            print.append(answer % MOD).append("\n");
        }
        System.out.println(print);
    }
}