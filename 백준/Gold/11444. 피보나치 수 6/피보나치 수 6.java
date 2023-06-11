import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long N;
    static long[][] fibonacciMatrix = new long[][]{{0, 1}, {1, 1}};

    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        long[][] answer = pow(N);
        System.out.println(answer[0][1]);
    }
    static long[][] matrixProd(long[][] A, long[][] B) {
        long value;
        long[][] answer = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                value = 0;
                for (int k = 0; k < 2; k++) {
                    value += (A[i][k] * B[k][j]) % 1000000007;
                    value %= 1000000007;
                }
                answer[i][j] = value;
            }
        }
        return answer;
    }
    static long[][] pow(long N) {
        long[][] res = new long[][]{{1, 0}, {0, 1}};
        long[][] answer = fibonacciMatrix;
        while (N > 0) {
            if (N % 2 == 1) {
                res = matrixProd(res, answer);
            }
            answer = matrixProd(answer, answer);
            N /= 2;
        }
        return res;
    }
}