import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = readInt(), M = readInt();
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
        br.close();
        int i = N - 1, j = M - 1, count = 0;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 1) {
                for (int k = 0; k <= i; k++) {
                    for (int l = 0; l <= j; l++) {
                        if (board[k][l] == 0) {
                            board[k][l] = 1;
                        } else board[k][l] = 0;
                    }
                }
                count++;
            }
            if (j == 0) {
                i--;
                j = M - 1;
            } else {
                j--;
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