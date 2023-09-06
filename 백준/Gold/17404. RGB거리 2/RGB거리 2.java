import java.io.IOException;

public class Main {
    static int N, min = Integer.MAX_VALUE;
    static int[][] expenses, dp;

    public static void main(String[] args) throws IOException {
        N = readInt();
        expenses = new int[N][3];
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                expenses[i][j] = readInt();
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) dp[0][j] = expenses[0][i];
                else dp[0][j] = 1000001;
            }
            for (int j = 1; j < N; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + expenses[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + expenses[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + expenses[j][2];
            }
            for (int j = 0; j < 3; j++) {
                if (i != j) min = Math.min(min, dp[N - 1][j]);
            }
        }
        System.out.println(min);
    }
    public static int readInt() throws IOException {
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