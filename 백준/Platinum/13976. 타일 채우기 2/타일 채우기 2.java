import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long N;

    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }
        N /= 2;
        System.out.println(power(N)[1][1]);

    }
    static long[][] prod(long[][] A, long[][] B) {
        long[][] answer = new long[2][2];
        long value;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                value = 0;
                for (int k = 0; k < 2; k++) {
                    value += A[i][k] * B[k][j];
                    value %= 1000000007;
                }
                answer[i][j] = value;
            }
        }
        return answer;
    }
    static long[][] power(long N) {
        long[][] res = {{1, 1}, {1, 3}};
        N--;
        long[][] ratio = {{0, -1}, {1, 4}};
        while (N > 0) {
            if (N % 2 == 1) {
                res = prod(res, ratio);
            }
            ratio = prod(ratio, ratio);
            N /= 2;
        }
        return res;
    }
}