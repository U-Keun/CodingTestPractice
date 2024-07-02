import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static long[][] adj;
    private static int N, K;
    private static final long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        adj = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adj[i][j] = Long.parseLong(st.nextToken());
            }
        }

        long[][] powerOfAdj = matrixPower(K);

        long answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += powerOfAdj[i][j];
                answer %= MOD;
            }
        }

        System.out.println(answer);
    }

    private static long[][] matrixProd(long[][] A, long[][] B) {
        long[][] answer = new long[N][N];
        long value;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                value = 0;
                for (int k = 0; k < N; k++) {
                    value += (A[i][k] * B[k][j]) % MOD;
                    value %= MOD;
                }
                answer[i][j] = value;
            }
        }
        return answer;
    }
    private static long[][] matrixPower(long B) {
        long[][] res = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) res[i][j] = 1;
            }
        }
        long[][] answer = adj;
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