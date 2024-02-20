import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        if (N == 1 || K == 0) {
            System.out.println(1);
            return;
        }
        int[][] dp = new int[N][K + 1];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < Math.min(K + 1, i + 2); j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                dp[i][j] %= 10007;
            }
        }
        System.out.println(dp[N - 1][K]);
    }
}