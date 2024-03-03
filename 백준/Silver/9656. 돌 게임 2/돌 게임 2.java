import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final String sk = "SK", cy = "CY";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] dp = new String[Math.max(3, N + 1)];
        dp[0] = sk;
        dp[1] = cy;
        dp[2] = sk;
        for (int i = 3; i <= N; i++) {
            if (dp[i - 1].equals(cy) && dp[i - 3].equals(cy)) {
                dp[i] = sk;
            } else dp[i] = cy;
        }
        System.out.println(dp[N]);
    }
}