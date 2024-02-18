import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            countCases(br, print);
            print.append("\n");
        }
        System.out.println(print);
    }
    private static void countCases(BufferedReader reader, StringBuilder print) throws IOException {
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int target = Integer.parseInt(reader.readLine());
        int[][] memo = new int[N + 1][target + 1];
        for (int i = 1; i <= N; i++) {
            int coin = Integer.parseInt(st.nextToken());
            if (coin <= target) {
                memo[i][coin] = 1;
            }
            for (int j = 1; j <= target; j++) {
                memo[i][j] += memo[i - 1][j] + memo[i][Math.max(0, j - coin)];
            }
        }
        print.append(memo[N][target]);
    }
}