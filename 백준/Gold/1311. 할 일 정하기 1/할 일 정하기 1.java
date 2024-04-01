import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int bit = 1 << N;
        int[][] dp = new int[N + 1][bit];
        for (int[] row : dp) Arrays.fill(row, 200001);
        dp[0][0] = 0;
        for (int i = 1; i <= N; i++) {
            int[] row = readIntegers(br.readLine());
            for (int j = 0; j < bit; j++) {
                if (dp[i - 1][j] == 200001) continue;
                for (int k = 0; k < N; k++) {
                    if ((j & (1 << k)) != 0) continue;
                    dp[i][j | (1 << k)] = Math.min(dp[i][j | (1 << k)], dp[i - 1][j] + row[k]);
                }
            }
        }
        br.close();
        System.out.println(dp[N][bit - 1]);
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}