import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[][] investTable = new int[M + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int amount = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= M; j++) {
                investTable[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[M + 1][N + 1];
        int[][] record = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) { // 20
            for (int j = 1; j <= N; j++) { // 6000
                for (int k = 0; k <= j; k++) { // 180000
                    int profit = dp[i - 1][j - k] + investTable[i][k];
                    if (profit > dp[i][j]) {
                        dp[i][j] = profit;
                        record[i][j] = k;
                    }
                }
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int company = M, amount = N;
        for (int i = M; i > 0; i--) {
            stack.push(record[company][amount]);
            amount -= record[company--][amount];
        }
        StringBuilder print = new StringBuilder();
        print.append(dp[M][N]).append("\n");
        while (!stack.isEmpty()) {
            print.append(stack.pop()).append(" ");
        }
        System.out.println(print);

    }
}