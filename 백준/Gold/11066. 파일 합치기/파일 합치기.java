import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int T = readInt();
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int K = readInt();
            int[] files = new int[K];
            int[] prefixSum = new int[K + 1];
            for (int j = 0; j < K; j++) {
                files[j] = readInt();
                prefixSum[j + 1] = prefixSum[j] + files[j];
            }
            int[][] dp = new int[K][K];
            for (int j = 1; j < K; j++) {
                for (int k = 1; k < K - j + 1; k++) {
                    int value = Integer.MAX_VALUE;
                    for (int l = 1; l <= j; l++) {
                        value = Math.min(value, dp[k + j - l][k + j - 1] + dp[k - 1][k + j - l - 1]);
                    }
                    dp[k - 1][k + j - 1] = value + prefixSum[k + j] - prefixSum[k - 1];
                }
            }
            print.append(dp[0][K - 1]).append("\n");
        }
        System.out.println(print);
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