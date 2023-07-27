import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] memo;
    static int n, k, value;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        memo = new int[k + 1];
        for (int i = 0; i < n; i++) {
            value = Integer.parseInt(br.readLine());
            if (value > k) continue;
            memo[value]++;
            for (int j = value + 1; j <= k; j++) {
                memo[j] += memo[j - value];
            }
        }
        System.out.println(memo[k]);
    }
}