import java.io.IOException;

public class Main {
    private static int N, M;
    private static int[][] map, memo;
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        map = new int[N][M];
        memo = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = readInt();
                memo[i][j] = -1;
            }
        }
        System.out.println(dfs(0, 0));
    }
    private static int dfs(int i, int j) {
        if (i == N - 1 && j == M - 1) {
            return 1;
        }
        int count = 0;
        for (int[] direction : directions) {
            int rowIndex = i + direction[0], colIndex = j + direction[1];
            if (rowIndex >= 0 && rowIndex < N
            && colIndex >= 0 && colIndex < M
            && map[i][j] > map[rowIndex][colIndex]) {
                if (memo[rowIndex][colIndex] != -1) count += memo[rowIndex][colIndex];
                else count += dfs(rowIndex, colIndex);
            }
        }
        memo[i][j] = count;
        return count;
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