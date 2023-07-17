import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, V, C, K;
    static int[] memo;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memo = new int[M + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            for (int j = 1; K > 0 ; j *= 2) {
                int count = Math.min(j, K);
                K -= count;
                for (int k = M; k >= count * V; k--) {
                    memo[k] = Math.max(memo[k], count * C + memo[k - count * V]);
                }
            }
        }
        System.out.println(memo[M]);
    }
}