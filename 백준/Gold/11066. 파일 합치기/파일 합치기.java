import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int T = readInt();
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int K = readInt();
            int[] files = new int[K], prefixSum = new int[K + 1];
            int[][] opt = new int[K][K];
            for (int j = 0; j < K; j++) {
                files[j] = readInt();
                prefixSum[j + 1] = prefixSum[j] + files[j];
                opt[j][j] = j;
            }
            int[][] dp = new int[K][K];
            for (int j = 1; j < K; j++) {
                for (int k = j; k < K; k++) {
                    int value = Integer.MAX_VALUE;
                    for (int l = opt[k - j][k - 1]; l <= Math.min(k - 1, opt[k - j + 1][k]); l++) {
                        if (value >= dp[k - j][l] + dp[l + 1][k]) {
                            opt[k - j][k] = l;
                            value = dp[k - j][l] + dp[l + 1][k];
                        }
                    }
                    dp[k - j][k] = value + prefixSum[k + 1] - prefixSum[k - j];
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