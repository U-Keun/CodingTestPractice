import java.util.Scanner;

public class Main {
    static final long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int p = sc.nextInt();
        long c = sc.nextLong();
        int k = sc.nextInt();

        long[] m = new long[p];

        for (int i = 0; i < p; i++) {
            m[i] = sc.nextLong();
        }

        long[] d = new long[p + 1];

        for (int i = 1; i < p; i++) {
            d[i] = m[i] - m[i - 1];
        }

        long dp = 0;
        long cp = c;

        for (int i = 1; i < p; i++) {
            dp += -cp * d[p - i];

            if (dp >= 0) {
                dp %= MOD;
            } else {
                dp = -(-dp % MOD);
            }

            cp *= c;
            cp %= MOD;
        }

        d[p] = dp;

        long ans = Math.abs(d[(k - 1) % p + 1]);
        int iter = (k - 1) / p;

        while (--iter >= 0) {
            ans *= cp;
            ans %= MOD;
        }

        System.out.println(ans);

        sc.close();
    }
}