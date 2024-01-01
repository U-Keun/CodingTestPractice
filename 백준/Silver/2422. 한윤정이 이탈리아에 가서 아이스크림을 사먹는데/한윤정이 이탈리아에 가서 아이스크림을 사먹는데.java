import java.io.IOException;

public class Main {
    private static boolean[][] taboo;
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        taboo = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            int a = readInt(), b = readInt();
            taboo[a - 1][b - 1] = true;
            taboo[b - 1][a - 1] = true;
        }
        int count = 0;
        for (int i = 1; i <= N - 2; i++) {
            for (int j = i + 1; j <= N - 1; j++) {
                if (taboo[i - 1][j - 1]) continue;
                for (int k = j + 1; k <= N; k++) {
                    if (taboo[i - 1][k - 1] || taboo[j - 1][k - 1]) continue;
                    count++;
                }
            }
        }
        System.out.println(count);
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