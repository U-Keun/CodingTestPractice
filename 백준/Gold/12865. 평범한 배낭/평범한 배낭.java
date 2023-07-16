import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[][] table, memo;


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        table = new int[N][2];
        memo = new int[N + 1][K + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (i == 0 || j == 0) memo[i][j] = 0;
                else if (table[i - 1][0] <= j) {
                    memo[i][j] = Math.max(table[i - 1][1] + memo[i - 1][j - table[i - 1][0]], memo[i - 1][j]);
                } else memo[i][j] = memo[i - 1][j];
            }
        }
        System.out.println(memo[N][K]);
    }
}