import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[][] matrices = new int[N][2];
        for (int i = 0; i < N; i++) {
            matrices[i][0] = readInt();
            matrices[i][1] = readInt();
        }
        int[][] dp = new int[N][N];
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N - i + 1; j++) {
                int value = Integer.MAX_VALUE;
                for (int k = 1; k <= i; k++) {
                    value = Math.min(value, dp[j + i - k][j + i - 1] + dp[j - 1][j + i - k - 1]
                            + matrices[j - 1][0] * matrices[j + i - k - 1][1] * matrices[j + i - 1][1]);
                }
                dp[j - 1][j + i - 1] = value;
            }
        }
        System.out.println(dp[0][N - 1]);
    }
    private static int readInt() throws IOException {
        int c, n;
        boolean isNegative = false;
        c = System.in.read();
        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }
        n = c & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return isNegative ? -n : n;
    }
}