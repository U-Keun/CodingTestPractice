import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        int[] dp = new int[K + 1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            if (coin > K || dp[coin] == 1) continue;
            for (int j = coin; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
        if (dp[K] == 10001) System.out.println(-1);
        else System.out.println(dp[K]);
    }
}