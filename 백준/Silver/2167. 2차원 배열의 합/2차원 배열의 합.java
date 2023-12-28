import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        int[][] prefix = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int number = readInt();
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + number;
            }
        }
        int K = readInt();
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int a = readInt(), b = readInt(), c = readInt(), d = readInt();
            print.append(prefix[c][d] - prefix[a - 1][d] - prefix[c][b - 1] + prefix[a - 1][b - 1]).append("\n");
        }
        System.out.println(print);
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
}