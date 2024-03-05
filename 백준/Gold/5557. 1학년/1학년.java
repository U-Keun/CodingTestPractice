import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        long[][] dp = new long[2][21];
        int first = Integer.parseInt(st.nextToken());
        dp[0][first] = 1;
        for (int i = 1; i < N - 1; i++) {
            int number = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 21; j++) {
                if (dp[0][j] > 0) {
                    if (j + number <= 20) {
                        dp[1][j + number] += dp[0][j];
                    }
                    if (j - number >= 0) {
                        dp[1][j - number] += dp[0][j];
                    }
                }
            }
            dp[0] = dp[1];
            dp[1] = new long[21];
        }
        int result = Integer.parseInt(st.nextToken());
        System.out.println(dp[0][result]);
    }
}