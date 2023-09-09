import java.io.IOException;
import java.util.HashMap;

public class Main {
    static long N, P, Q;
    static HashMap<Long, Long> dp = new HashMap<>();
    public static void main(String[] args) throws IOException {
        N = readLong();
        P = readLong();
        Q = readLong();
        dp.put(0L, 1L);
        System.out.println(dp(N));
    }
    public static long readLong() throws IOException {
        long val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
    public static long dp(long n) {
        if (dp.containsKey(n)) return dp.get(n);
        long x = n / P;
        long y = n / Q;
        dp.put(n, dp(x) + dp(y));
        return dp.get(n);
    }
}