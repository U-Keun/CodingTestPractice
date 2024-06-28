import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long[][] FIBONACCI = {{0, 1}, {1, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        long a = Long.parseLong(st.nextToken()), b = Long.parseLong(st.nextToken());

        long lower = matrixPower(a)[1][1],
            upper = matrixPower(b + 1)[1][1];

        long answer = upper - lower;
        if (answer < 0) {
            System.out.println(answer + 1_000_000_000);
            return;
        }
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
                answer[i][j] = value % 1_000_000_000;
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