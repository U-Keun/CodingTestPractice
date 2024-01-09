import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] firstRow = br.readLine().split(" ");
            int p = Integer.parseInt(firstRow[0]);
            long c = Long.parseLong(firstRow[1]);
            int k = Integer.parseInt(firstRow[2]);
            String[] secondRow = br.readLine().split(" ");
            long[] deposit = new long[p];
            for (int i = 0; i < p; i++) {
                deposit[i] = Long.parseLong(secondRow[i]);
            }
            long[] record = new long[p + 1];

            for (int i = 1; i < p; i++) {
                record[i] = deposit[i] - deposit[i - 1];
            }
            long Dp = 0, cp = c;
            for (int i = 1; i < p; i++) {
                Dp -= cp * record[p - i];
                if (Dp >= 0) Dp %= MOD;
                else Dp = - (- Dp % MOD);
                cp *= c;
                cp %= MOD;
            }
            record[p] = Dp;
            long answer = Math.abs(record[(k - 1) % p + 1]);
            while (k > p) {
                answer *= cp;
                answer %= MOD;
                k -= p;
            }
            bw.write(String.valueOf(answer));
            bw.flush();
        }
    }
}