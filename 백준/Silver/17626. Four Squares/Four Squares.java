import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] memo;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        memo = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            memo[i] = i;
            for (int j = 1; j * j <= i; j++) {
                memo[i] = Math.min(memo[i], memo[i - j * j] + 1);
            }
        }
        System.out.println(memo[n]);
    }
}