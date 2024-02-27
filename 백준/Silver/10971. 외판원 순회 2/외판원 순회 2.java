import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, min = Integer.MAX_VALUE;
    private static int[][] W, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][1 << N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][1] = 0;
        dfs(0, 1);
        System.out.println(min);
    }
    private static void dfs(int current, int visited) {
        if (visited == (1 << N) - 1) {
            if (W[current][0] == 0) return;
            min = Math.min(min, dp[current][visited] + W[current][0]);
            return;
        }
        for (int i = 0; i < N; i++) {
            if ((visited | (1 << i)) == visited || W[current][i] == 0) continue;
            if (dp[current][visited] + W[current][i] < dp[i][visited | (1 << i)]) {
                dp[i][visited | (1 << i)] = dp[current][visited] + W[current][i];
                dfs(i, visited | (1 << i));
            }
        }
    }
}