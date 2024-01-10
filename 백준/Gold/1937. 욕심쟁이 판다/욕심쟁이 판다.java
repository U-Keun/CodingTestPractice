import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[][] map, memo;
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        N = readInt();
        map = new int[N][N];
        memo = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = readInt();
                memo[i][j] = -1;
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (memo[i][j] == -1) {
                    max = Math.max(max, dfs(i, j));
                }
            }
        }
        System.out.println(max);
    }
    private static int dfs(int i, int j) {
        int count = 1;
        for (int[] direction : directions) {
            int rowIndex = i + direction[0], colIndex = j + direction[1];
            if (rowIndex >= 0 && rowIndex < N
            && colIndex >= 0 && colIndex < N
            && map[i][j] < map[rowIndex][colIndex]) {
                if (memo[rowIndex][colIndex] != -1) count = Math.max(count, memo[rowIndex][colIndex] + 1);
                else count = Math.max(count, dfs(rowIndex, colIndex) + 1);
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