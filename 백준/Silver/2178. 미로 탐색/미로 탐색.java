import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, path = 0, l, nextX, nextY;
    static int[][] board;
    static Queue<Integer> X = new LinkedList<>();
    static Queue<Integer> Y = new LinkedList<>();
    static int[] x = new int[]{-1, 0, 0, 1}, y = new int[]{0, 1, -1, 0};
    static String[] input;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
        X.add(0);
        Y.add(0);
        board[0][0] = -1;
        Loop : while (X.size() > 0) {
            l = X.size();
            path++;
            for (int i = 0; i < l; i++) {
                nextX = X.poll();
                nextY = Y.poll();
                if (nextY == N - 1 && nextX == M - 1) break Loop;
                for (int j = 0; j < 4; j++) {
                    if (nextY + y[j] >= 0 && nextY + y[j] < N
                        && nextX + x[j] >= 0 && nextX + x[j] < M
                        && board[nextY + y[j]][nextX + x[j]] == 1) {
                        Y.add(nextY + y[j]);
                        X.add(nextX + x[j]);
                        board[nextY + y[j]][nextX + x[j]] = -1;
                    }
                }
            }
        }
        System.out.println(path);
    }
}