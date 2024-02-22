import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        for (int i = 1; i * i <= N; i++) {
            dp[i * i] = 1;
        }
        for (int i = 1; i <= N; i++) {
            if (dp[i] != 0) continue;
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
            }
        }
        System.out.println(dp[N]);
    }
}