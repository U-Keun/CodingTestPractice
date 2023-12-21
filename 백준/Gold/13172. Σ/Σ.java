import java.io.IOException;

public class Main {
    private static final long MOD = 1000000007L;
    public static void main(String[] args) throws IOException {
        int M = readInt();
        long answer = 0;
        for (int i = 0; i < M; i++) {
            int N = readInt(), S = readInt();
            int gcd = gcd(N, S);
            int a = N / gcd, b = S / gcd;
            answer += modPow(N, MOD - 2) * S;
            answer %= MOD;
        }
        System.out.println(answer);
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }

    private static long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }
    private static int gcd(int n, int s) {
        int a = Math.max(n, s);
        int b = Math.min(n, s);
        while ( a % b != 0 ) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return b;
    }
}