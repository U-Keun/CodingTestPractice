import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        String[] firstRow = br.readLine().split(" ");
        int p = Integer.parseInt(firstRow[0]);
        long c = Long.parseLong(firstRow[1]);
        int k = Integer.parseInt(firstRow[2]);

        long[] m = new long[p];
        String[] secondRow = br.readLine().split(" ");
        br.close();
        for (int i = 0; i < p; i++) {
            m[i] = Long.parseLong(secondRow[i]);
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