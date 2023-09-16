import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[][] chessBoard;
    static int cases = 0, N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        br.close();
        chessBoard = new boolean[N][N];
        recursion(0);
        bw.write(String.valueOf(cases));
        bw.flush();
        bw.close();
    }
    public static void recursion(int k) {
        if (k == N) {
            cases++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (check(k , i)) {
                chessBoard[k][i] = true;
                recursion(k + 1);
                chessBoard[k][i] = false;
            }
        }

    }
    public static boolean check(int x, int y) {
        for (int i = 0; i < N; i++) {
            if (y != i && chessBoard[x][i]) return false;
        }
        for (int i = 0; i < N; i++) {
            if (x != i && chessBoard[i][y]) return false;
        }
        for (int i = Math.max(x - y, 0), j = i + y - x; i < N && j < N; i++, j++) {
            if (x != i && chessBoard[i][j]) return false;
        }
        for (int i = Math.max(0, y + x - N + 1), j = -i + y + x; i < N && j >= 0; i++, j--) {
            if (x != i && chessBoard[i][j]) return false;
        }
        return true;
    }
}