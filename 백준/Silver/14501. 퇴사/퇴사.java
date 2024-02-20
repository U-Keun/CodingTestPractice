import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] T = new int[N], P = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[2][N + 1];
        for (int i = N - 1; i >= 0 ; i--) {
            if (i + T[i] > N) {
                dp[1][i] = dp[1][i + 1];
                continue;
            }
            dp[0][i] = P[i] + dp[1][i + T[i]];
            dp[1][i] = Math.max(dp[0][i], dp[1][i + 1]);
        }
        System.out.println(dp[1][0]);
    }
}