import java.io.IOException;

public class Main {
    private static final long[][] FIBONACCI = {{0, 1}, {1, 1}};
    public static void main(String[] args) throws IOException {
        long N = readInt();
        if (N == 0) {
            System.out.println(0);
            return;
        }
        System.out.println(matrixPower(N - 1)[1][1]);
    }
    private static long readInt() throws IOException {
        int c;
        long n;
        boolean isNegative = false;
        c = System.in.read();
        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }
        n = c & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return isNegative ? -n : n;
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
                answer[i][j] = value % 1000000;
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