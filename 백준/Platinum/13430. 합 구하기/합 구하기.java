import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        long k = Long.parseLong(st.nextToken()), n = Long.parseLong(st.nextToken());

        System.out.println(binomial(n + k, k + 1));
    }

    private static long modPower(long a, long n) {
        long answer = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                answer = (answer * a) % MOD;
            }
            a = (a * a) % MOD;
            n >>= 1;
        }
        return answer;
    }
    private static long binomial(long n, long k) {
        if (k > n || n < 0) return 0;
        long num = 1, denom = 1;
        for (int i = 0; i < k; i++) {
            num = (num * (n - i)) % MOD;
            denom = (denom * (i + 1)) % MOD;
        }
        return (num * modPower(denom, MOD - 2)) % MOD;
    }
}