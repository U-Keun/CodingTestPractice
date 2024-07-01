import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long[][] FIBONACCI = {{0, 1}, {1, 1}};
    private static final long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        long n = Long.parseLong(st.nextToken()), m = Long.parseLong(st.nextToken());

        if (n == 0 && m == 0) {
            System.out.println(0);
            return;
        }
        System.out.println(matrixPower(gcd(n, m) - 1)[1][1]);
    }

    private static long[][] matrixProd(long[][] A, long[][] B) {
        long[][] answer = new long[2][2];
        long value;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                value = 0;
                for (int k = 0; k < 2; k++) {
                    value += A[i][k] * B[k][j];
                }
                answer[i][j] = value % MOD;
            }
        }
        return answer;
    }
    private static long[][] matrixPower(long B) {
        long[][] res = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == j) res[i][j] = 1;
            }
        }
        long[][] answer = FIBONACCI;
        while (B > 0) {
            if (B % 2 == 1) {
                res = matrixProd(res, answer);
            }
            answer = matrixProd(answer, answer);
            B /= 2;
        }
        return res;
    }
    private static long gcd(long a, long b) {
        if (a < b) b %= a;

        while (b > 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }

        return a;
    }
}