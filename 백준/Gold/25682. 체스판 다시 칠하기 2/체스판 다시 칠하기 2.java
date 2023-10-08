import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[][] boardWhite, boardBlack;
    private static char[] input;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            boardWhite = new int[N + 1][M + 1];
            boardBlack = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                input = br.readLine().toCharArray();
                for (int j = 1; j <= M; j++) {
                    dp(i, j);
                }
            }
            int answer = Integer.MAX_VALUE;
            for (int i = K; i <= N; i++) {
                for (int j = K; j <= M; j++) {
                    answer = Math.min(answer, Math.min(boardWhite[i][j] - boardWhite[i - K][j] - boardWhite[i][j - K] + boardWhite[i - K][j - K],
                            boardBlack[i][j] - boardBlack[i - K][j] - boardBlack[i][j - K] + boardBlack[i - K][j - K]));
                }
            }
            bw.write(String.valueOf(answer));
            bw.flush();
        }
    }
    private static void dp(int i, int j) {
        boardWhite[i][j] += boardWhite[i - 1][j];
        boardBlack[i][j] += boardBlack[i - 1][j];
        boardWhite[i][j] += boardWhite[i][j - 1];
        boardBlack[i][j] += boardBlack[i][j - 1];
        boardWhite[i][j] -= boardWhite[i - 1][j - 1];
        boardBlack[i][j] -= boardBlack[i - 1][j - 1];
        if ((i + j) % 2 == 0) {
            if (input[j - 1] == 'B') boardWhite[i][j]++;
            else boardBlack[i][j]++;
        } else {
            if (input[j - 1] == 'B') boardBlack[i][j]++;
            else boardWhite[i][j]++;
        }
    }
}