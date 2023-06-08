import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int T, N, M, K, answer;
    static int[][] field;
    static int[] coord;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            field = new int[N][M];
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                coord = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
                field[coord[1]][coord[0]] = 1;
            }
            answer = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (field[j][k] == 1) {
                        dfs(j, k);
                        answer++;
                    }
                }
            }
            print.append(answer).append('\n');
        }
        System.out.println(print);
    }
    static void dfs(int j, int i) {
        int[] x = {-1, 0, 0, 1};
        int[] y = {0, -1, 1, 0};
        if (field[j][i] == -1 || field[j][i] == 0) {
            return;
        }
        field[j][i] = -1;
        for (int k = 0; k < 4; k++) {
            int newX = i + x[k];
            int newY = j + y[k];
            if (newY >= 0 && newY < N && newX >= 0 && newX < M) {
                dfs(newY, newX);
            }
        }
    }
}