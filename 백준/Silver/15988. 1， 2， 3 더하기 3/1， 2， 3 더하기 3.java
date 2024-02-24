import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[1000001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        int previous = 4;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n < previous) {
                print.append(dp[n]).append("\n");
                continue;
            }
            for (int j = previous; j <= n; j++) {
                dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
                dp[j] %= 1000000009;
            }
            print.append(dp[n]).append("\n");
            previous = n + 1;
        }
        System.out.println(print);
    }
}