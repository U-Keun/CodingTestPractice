import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int[][] adj;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        adj = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
                if (adj[i][j] == 0) adj[i][j] = 101;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    adj[j][k] = Math.min(adj[j][k], adj[j][i] + adj[i][k]);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (adj[i][j] == 101) print.append("0 ");
                else print.append("1 ");
            }
            print.append('\n');
        }
        System.out.println(print);
    }
}