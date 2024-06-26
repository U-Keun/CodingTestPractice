import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 4_000_000;
    private static final long mod = 1_000_000_007;

    private static final long[] factorial = new long[MAX + 1];
    private static final long[] invFactorial = new long[MAX + 1];
    
    static {
        factorial[0] = 1;
        for (int i = 1; i <= MAX; i++) {
            factorial[i] = factorial[i - 1] * i % mod;
        }
        invFactorial[MAX] = modPower(factorial[MAX], mod - 2);
        for (int i = MAX - 1; i >= 0; i--) {
            invFactorial[i] = invFactorial[i + 1] * (i + 1) % mod;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        StringTokenizer st;
        int N, K;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            print.append(binomial(N, K));
            if (M > 0) print.append("\n");
        }
        br.close();

        System.out.println(print);
    }
    private static long modPower(long a, long n) {
        long answer = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                answer = (answer * a) % mod;
            }
            a = (a * a) % mod;
            n >>= 1;
        }
        return answer;
    }
    private static long binomial(long n, long k) {
        if (k > n || n < 0) return 0;
        return factorial[(int)n] * invFactorial[(int)k] % mod * invFactorial[(int)(n - k)] % mod;
    }
}