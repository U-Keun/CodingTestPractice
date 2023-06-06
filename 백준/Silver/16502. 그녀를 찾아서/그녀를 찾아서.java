import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static StringTokenizer st;
    static int N, M, i, j;
    static float[][] adj = new float[4][4];
    static float[][] prob;
    static float val;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            i = st.nextToken().charAt(0) - 'A';
            j = st.nextToken().charAt(0) - 'A';
            adj[i][j] = Float.parseFloat(st.nextToken());
        }
        prob = matrixPow(N);
        for (int k = 0; k < 4; k++) {
            val = 0;
            for (int l = 0; l < 4; l++) {
                val += prob[l][k];
            }
            val = (float) (Math.round(val * 2500) / 100.0);
            print.append(String.format("%.2f", val)).append('\n');
        }
        System.out.println(print);
    }
    static float[][] matrixProd(float[][] A, float[][] B) {
        float[][] answer = new float[4][4];
        float value;
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                value = 0;
                for (int m = 0; m < 4; m++) {
                    value += A[k][m] * B[m][l];
                }
                answer[k][l] = value;
            }
        }
        return answer;
    }
    static float[][] matrixPow(int N) {
        float[][] res = {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
        float[][] answer = adj;
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