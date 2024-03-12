import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer L = new StringTokenizer(br.readLine()), J = new StringTokenizer(br.readLine());
        int[] dp = new int[101];
        for (int i = 0; i < N; i++) {
            int l = Integer.parseInt(L.nextToken()), j = Integer.parseInt(J.nextToken());
            for (int k = 100; k > l ; k--) {
                dp[k] = Math.max(dp[k], dp[k - l] + j);
            }
        }
        System.out.println(dp[100]);
    }
}