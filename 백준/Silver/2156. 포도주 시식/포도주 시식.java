import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[2][N];
        for (int i = 0; i < N; i++) dp[0][i] = Integer.parseInt(br.readLine());
        br.close();
        if (N == 1) {
            bw.write(String.valueOf(dp[0][0]));
            bw.flush();
            bw.close();
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < N; i++) {
            dp[1][i] = dp[0][i - 1] + dp[0][i];
            if (i == 1) dp[0][i] = Math.max(dp[0][i - 1], dp[0][i]);
            else if (i == 2) dp[0][i] = Math.max(dp[0][i - 2], dp[1][i - 2]) + dp[0][i];
            else dp[0][i] = Math.max(Math.max(dp[0][i - 2], dp[1][i - 2]), Math.max(dp[0][i - 3], dp[1][i - 3])) + dp[0][i];

            if (i == N - 2 || i == N - 1) max = Math.max(max, Math.max(dp[0][i], dp[1][i]));
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}