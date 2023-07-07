import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, T, P;
    static int[] memo;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        memo = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            memo[i+1] = Math.max(memo[i+1], memo[i]);
            if(i + T <= N) {
                memo[i + T] = Math.max(memo[i + T], memo[i] + P);
            }
        }
        System.out.println(memo[N]);
    }
}