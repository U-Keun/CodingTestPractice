import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static final long mod = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(binomial(N, K)));
            bw.flush();
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
    private static long binomial(int n, int k) {
        if (k > n || n < 0) return 0;
        long num = 1, denom = 1;
        for (int i = 0; i < k; i++) {
            num = (num * (n - i)) % mod;
            denom = (denom * (i + 1)) % mod;
        }
        return (num * modPower(denom, mod - 2)) % mod;
    }
}