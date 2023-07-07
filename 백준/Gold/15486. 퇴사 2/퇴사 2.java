import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] table;
    static int[] memo;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        table = new int[N][2];
        memo = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
            memo[i+1] = Math.max(memo[i+1], memo[i]);
            if(i + table[i][0] <= N) {
                memo[i + table[i][0]] = Math.max(memo[i + table[i][0]], memo[i] + table[i][1]);
            }
        }
        System.out.println(memo[N]);
    }
}