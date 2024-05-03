import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        long[][] dp;
        while (t != 0) {
            dp = new long[t][2 * t + 1];
            dp[t - 1][1] = 1;
            for (int i = 1; i < 2 * t; i++) {
                for (int j = t - 1; j >= 0; j--) {
                    if (dp[j][i] == 0) continue;
                    if ((t - j) * 2 > i) {
                        dp[j][i + 1] += dp[j][i];
                    }
                    if (j > 0) {
                        dp[j - 1][i + 1] += dp[j][i];
                    }
                }
            }
            print.append(dp[0][2 * t]).append("\n");
            t = Integer.parseInt(br.readLine());
        }
        br.close();
        System.out.println(print);
    }
}