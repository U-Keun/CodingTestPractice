import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MAX = 52;
    private static final long mod = 10_007;

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
        int N = Integer.parseInt(br.readLine());
        long answer = 0, index = 1;
        N -= 4;
        while (N >= 0) {
            if (index % 2 == 1) {
                answer += binomial(13, index) * binomial(52 - 4 * index, N);
            } else {
                answer -= binomial(13, index) * binomial(52 - 4 * index, N);

            }
            answer %= mod;

            N -= 4;
            index++;
        }

        if (answer < 0) {
            System.out.println(answer + mod);
        } else {
            System.out.println(answer);
        }
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