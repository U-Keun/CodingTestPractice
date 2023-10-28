import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            StringTokenizer st;
            for (int i = 0; i < T; i++) {
                int N = Integer.parseInt(br.readLine());
                int[][] costs = new int[2][N];
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    costs[0][j] = Integer.parseInt(st.nextToken());
                }
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    costs[1][j] = Integer.parseInt(st.nextToken());
                }
                bw.write(String.valueOf(dp(costs)));
                bw.write("\n");
            }
            bw.flush();
        }
    }
    private static int dp(int[][] costs) {
        int n = costs[0].length;
        int[][] dp = new int[2][n];
        dp[0][0] = costs[0][0];
        dp[1][0] = costs[1][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + costs[0][i]);
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] + costs[1][i]);
        }
        return Math.max(dp[0][n - 1], dp[1][n - 1]);
    }
}