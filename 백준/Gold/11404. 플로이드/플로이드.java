import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static StringTokenizer st;
    static int n, bus, a, b, c;
    static int[][] adj;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        adj = new int[n][n];
        bus = Integer.parseInt(br.readLine());
        for (int i = 0; i < bus; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());
            adj[a][b] = (adj[a][b] == 0)? c: (Math.min(adj[a][b], c));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && adj[i][j] == 0) adj[i][j] = 10000001;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    adj[j][k] = Math.min(adj[j][k], adj[j][i] + adj[i][k]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adj[i][j] == 10000001) print.append("0 ");
                else print.append(adj[i][j]).append(" ");
            }
            print.append('\n');
        }
        System.out.println(print);
    }
}