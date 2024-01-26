import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static final long mod = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            long value = 6;
            long answer = 0;
            for (int i = 3; i <= M; i++) {
                value = (value * (4L * i - 2)) % mod;
                value = (value * modPower(i, mod - 2)) % mod;
                answer += value;
                answer %= mod;
            }
            bw.write(String.valueOf(answer));
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
}