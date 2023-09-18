import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final long mod = 1000000000;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        br.close();
        long[][] dp = new long[N + 1][10];
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) dp[i][j] = dp[i - 1][j + 1] % mod;
                else if (j == 9) dp[i][j] = dp[i - 1][j - 1] % mod;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1] % mod;
            }
        }
        long value = 0;
        for (int i = 0; i <= 9; i++) {
            value += dp[N][i] % mod;
            value %= mod;
        }
        bw.write(String.valueOf(value));
        bw.flush();
        bw.close();
    }
}