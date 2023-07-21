import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int C, N, cost, clients;
    static int[] memo;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        memo = new int[C + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost = Integer.parseInt(st.nextToken());
            clients = Integer.parseInt(st.nextToken());
            for (int k = C; k >= 1; k--) {
                if (memo[k] == 0) {
                    memo[k] = (int) Math.ceil((double) k / clients) * cost;
                } else {
                    for (int j = 1; j <= (int) Math.ceil((double) k / clients); j++) {
                        if (k - j * clients < 0) {
                            memo[k] = Math.min(memo[k], j * cost);
                        } else memo[k] = Math.min(memo[k], j * cost + memo[k - j * clients]);
                    }
                }

            }
        }
        System.out.println(memo[C]);
    }
}