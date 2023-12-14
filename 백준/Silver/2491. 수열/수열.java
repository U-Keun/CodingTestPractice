import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[][] dp = new int[n][2];
        int prev = readInt();
        dp[0][0] = 1;
        dp[0][1] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            int tmp = readInt();
            if (tmp > prev) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = 1;
            } else if (tmp < prev) {
                dp[i][0] = 1;
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] + 1;
            }
            prev = tmp;
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
        System.out.println(max);
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
}