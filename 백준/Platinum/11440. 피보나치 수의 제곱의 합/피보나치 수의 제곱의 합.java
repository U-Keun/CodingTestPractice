import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final long[][] FIBONACCI = {{0, 1}, {1, 1}};
    private static final long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        br.close();

        long answer = matrixPower(n - 1)[1][1];
        answer *= matrixPower(n)[1][1];
        answer %= MOD;

        System.out.println(answer);
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
}