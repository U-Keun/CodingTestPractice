import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean[] isNotPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isNotPrime = new boolean[1000001];
        sieve();
        int n = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        while (n != 0) {
            for (int j = 2; j < n / 2 + 1; j++) {
                if (isNotPrime[j] || isNotPrime[n - j]) continue;
                print.append(n)
                        .append(" = ").append(j).append(" + ").append(n - j)
                        .append("\n");
                break;
            }
            n = Integer.parseInt(br.readLine());
        }
        br.close();
        System.out.println(print);
    }
    private static void sieve() {
        for (int i = 2; i <= 1000; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= 1000000; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }
}