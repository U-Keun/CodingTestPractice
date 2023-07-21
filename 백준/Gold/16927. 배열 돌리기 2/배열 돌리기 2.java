import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int N, M, R, layers, perimeter;
    static int[][] array;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        array = new int[N][M];
        layers = Math.min(N, M) / 2;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < layers; i++) {
            perimeter = 2 * (N + M - 2 - 4 * i);
            for (int j = 0; j < R % perimeter; j++) {
                int tmp = array[i][i];
                for (int k = i + 1; k < M - i; k++) {
                    array[i][k - 1] = array[i][k];
                }
                for (int k = i + 1; k < N - i; k++) {
                    array[k - 1][M - i - 1] = array[k][M - i - 1];
                }
                for (int k = M - i - 2; k >= i; k--) {
                    array[N - i - 1][k + 1] = array[N - i - 1][k];
                }
                for (int k = N - i - 1; k > i + 1 ; k--) {
                    array[k][i] = array[k - 1][i];
                }
                array[i + 1][i] = tmp;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                print.append(array[i][j]).append(" ");
            }
            print.append('\n');
        }
        System.out.println(print);
    }
}