import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C, count, max;
    static char[][] board;
    static Set<Character> visited;
    static int[] x = {-1, 0, 0, 1}, y = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }
        visited = new HashSet<>();
        count = 1;
        max = Integer.MIN_VALUE;
        dfs(0, 0);
        System.out.println(max);
    }
    static void dfs(int i, int j) {
        if (visited.contains(board[i][j])) {
            return;
        }
        if (max < count) max = count;
        for (int k = 0; k < 4; k++) {
            visited.add(board[i][j]);
            count++;
            if (i + y[k] >= 0 && i + y[k] < R
                && j + x[k] >= 0 && j + x[k] < C) {
                dfs(i + y[k], j + x[k]);
            }
            visited.remove(board[i][j]);
            count--;
        }
    }
}