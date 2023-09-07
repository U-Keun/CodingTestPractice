import java.io.IOException;

public class Main {
    static StringBuilder print = new StringBuilder();
    static int N, M;
    static int[] sequence;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        N = readInt();
        sequence = new int[N + 1];
        dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            sequence[i] = readInt();
        }
        for (int i = N; i > 0; i--) {
            for (int j = i; j <= N; j++) {
                if (i == j) dp[i][j] = 1;
                else if (j - i == 1) {
                    if (sequence[i] == sequence[j]) dp[i][j] = 1;
                } else {
                    if (sequence[i] == sequence[j] && dp[i + 1][j - 1] == 1) dp[i][j] = 1;
                }
            }
        }
        M = readInt();
        for (int i = 0; i < M; i++) {
            int S = readInt(), E = readInt();
            print.append(dp[S][E]).append('\n');
        }
        System.out.println(print);
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