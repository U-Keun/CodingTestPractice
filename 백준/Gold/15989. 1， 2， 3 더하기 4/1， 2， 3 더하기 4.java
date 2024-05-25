import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[10001];
        dp[0] = 1;
        for (int i = 1; i <= 3; i++) {
            for (int j = i; j < 10001; j++) {
                dp[j] += dp[j - i];
            }
        }
        StringBuilder print = new StringBuilder();
        int input;
        for (int i = 0; i < N; i++) {
           input = Integer.parseInt(br.readLine());
           print.append(dp[input]).append("\n");
        }
        System.out.println(print);
    }
}