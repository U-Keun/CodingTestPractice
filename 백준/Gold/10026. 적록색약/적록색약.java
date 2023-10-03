import java.io.*;

public class Main {
    private static char[][] board;
    private static boolean[][] visited;
    private static int N;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            N = Integer.parseInt(br.readLine());
            board = new char[N][N];
            for (int i = 0; i < N; i++) {
                board[i] = br.readLine().toCharArray();
            }
            visited = new boolean[N][N];
            int count;
            count = getCount();
            StringBuilder print = new StringBuilder();
            print.append(count).append(" ");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 'G') board[i][j] = 'R';
                }
            }
            visited = new boolean[N][N];
            count = getCount();
            print.append(count);
            bw.write(print.toString());
            bw.flush();
        }
    }

    private static int getCount() {
        int value = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    value++;
                    find(i, j);
                }
            }
        }
        return value;
    }

    private static void find(int rowIdx, int colIdx) {
        int[][] vectors = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        char color = board[rowIdx][colIdx];
        for (int[] vector:vectors) {
            int newRowIdx = rowIdx + vector[0], newColIdx = colIdx + vector[1];
            if (newRowIdx >= 0 && newRowIdx < N
            && newColIdx >= 0 && newColIdx < N
            && !visited[newRowIdx][newColIdx] && board[newRowIdx][newColIdx] == color) {
                visited[newRowIdx][newColIdx] = true;
                find(newRowIdx, newColIdx);
            }
        }
    }
}