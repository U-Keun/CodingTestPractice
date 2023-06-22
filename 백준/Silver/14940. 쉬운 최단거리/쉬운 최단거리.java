import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int n, m, l, dist = 0;
    static int[][] board;
    static boolean[][] visited;
    static Queue<int[]> location = new LinkedList<>();
    static int[] x = {-1, 0, 0, 1}, y = {0, 1, -1, 0}, k;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) location.add(new int[]{i, j});
            }
        }
        while (!location.isEmpty()) {
            l = location.size();
            for (int i = 0; i < l; i++) {
                k = location.poll();
                board[k[0]][k[1]] = dist;
                for (int j = 0; j < 4; j++) {
                    int newX = k[1] + x[j];
                    int newY = k[0] + y[j];
                    if (newY >= 0 && newY < n
                        && newX >= 0 && newX < m
                        && !visited[newY][newX]
                        && board[newY][newX] != 0) {
                        visited[newY][newX] = true;
                        location.add(new int[]{newY, newX});
                    }
                }
            }
            dist++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 0 && !visited[i][j]) print.append(-1).append(" ");
                else print.append(board[i][j]).append(" ");
            }
            print.append('\n');
        }
        System.out.println(print);
    }
}