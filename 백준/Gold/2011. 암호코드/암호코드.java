import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        br.close();
        int n = input.length();
        int[] numbers = input.chars()
                .map(c -> c - '0')
                .toArray();
        long[] dp = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            if (numbers[i] == 0) {
                if (i >= 1 && numbers[i - 1] <= 2) {
                    dp[i] = 0;
                    continue;
                }
                System.out.println(0);
                return;
            } else {
                if (i == n - 1 || (i == n - 2 && numbers[i + 1] == 0)) {
                    dp[i] = 1;
                } else if (i == n - 2 && numbers[i] * 10 + numbers[i + 1] <= 26) {
                    dp[i] = 2;
                } else {
                    dp[i] += dp[i + 1];
                    if (i + 2 < n && numbers[i] * 10 + numbers[i + 1] <= 26) {
                        dp[i] += dp[i + 2];
                    }
                    dp[i] %= 1000000;
                }
            }
        }
        System.out.println(dp[0]);
    }
}