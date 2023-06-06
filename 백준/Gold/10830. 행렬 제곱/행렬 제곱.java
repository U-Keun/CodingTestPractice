import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int N;
    static long B;
    static long[][] matrix;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        matrix = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Long.parseLong(st.nextToken());
            }
        }
        long[][] answer = matrixPower(B);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                print.append(answer[i][j]).append(" ");
            }
            print.append('\n');
        }
        System.out.println(print);
    }
    static long[][] matrixProd(long[][] A, long[][] B) {
        long[][] answer = new long[N][N];
        long value;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                value = 0;
                for (int k = 0; k < N; k++) {
                    value += A[i][k] * B[k][j];
                }
                answer[i][j] = value % 1000;
            }
        }
        return answer;
    }
    static long[][] matrixPower(long B) {
        long[][] res = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) res[i][j] = 1;
            }
        }
        long[][] answer = matrix;
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