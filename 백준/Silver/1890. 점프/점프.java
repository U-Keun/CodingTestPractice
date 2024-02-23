import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][N + 1];
        dp[1][1] = 1;
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int jump = Integer.parseInt(st.nextToken());
                if (jump == 0) continue;
                if (i + jump <= N) {
                    dp[i + jump][j] += dp[i][j];
                }
                if (j + jump <= N) {
                    dp[i][j + jump] += dp[i][j];
                }
            }
        }
        System.out.println(dp[N][N]);
    }
}