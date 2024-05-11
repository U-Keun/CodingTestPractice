import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean[] isNotPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isNotPrime = new boolean[10001];
        sieve();
        int T = Integer.parseInt(br.readLine());
        int n;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            int candidate = 0;
            for (int j = 2; j < n / 2 + 1; j++) {
                if (isNotPrime[j] || isNotPrime[n - j]) continue;
                candidate = j;
            }
            print.append(candidate).append(" ").append(n - candidate);
            print.append("\n");
        }
        br.close();
        System.out.println(print);
    }
    private static void sieve() {
        for (int i = 2; i <= 10000; i++) {
            if (!isNotPrime[i]) {
                for (int j = i + i; j <= 10000; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }
}