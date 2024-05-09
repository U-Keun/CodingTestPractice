import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stones = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        long[] dp = new long[N];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.min(dp[i], Math.max(dp[j], (long) (i - j) * (1 + Math.abs(stones[i] - stones[j]))));
            }
        }
        System.out.println(dp[N - 1]);
    }
}