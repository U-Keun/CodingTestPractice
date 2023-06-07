import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] board = new int[5][5];
    static int[] start = new int[2];
    static int[] x = {-1, 0, 0, 1}, y = {0, -1, 1, 0};
    static int move;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());
        System.out.println(dfs(start, 3, 0));
    }
    static int dfs(int[] start, int move, int apples) {
        int answer = 0;
        int tmp = board[start[0]][start[1]];
        if (tmp == 1) apples++;
        if (move == 0 || board[start[0]][start[1]] == -1) {
            if (apples >= 2) return 1;
            return answer;
        }
        int newX, newY;
        for (int i = 0; i < 4; i++) {
            newX = start[1] + x[i];
            newY = start[0] + y[i];
            if (newX >= 0 && newX <= 4 && newY >= 0 && newY <= 4) {
                board[start[0]][start[1]] = -1;
                answer = dfs(new int[]{newY, newX}, move - 1, apples);
                if (answer == 1) break;
                board[start[0]][start[1]] = tmp;
            }
        }
        return answer;
    }
}