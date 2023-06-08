import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, value, max;
    static String input;
    static int[][] adj, matrix;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        adj = new int[N][N];
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < N; j++) {
                if (input.charAt(j) == 'Y') adj[i][j] = 1;
            }
        }
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                value = 0;
                for (int k = 0; k < N; k++) {
                    value += adj[i][k] * adj[k][j];
                }
                matrix[i][j] = value + adj[i][j];
            }
        }
        max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            value = 0;
            for (int j = 0; j < N; j++) {
                if (i != j && matrix[i][j] != 0) value++;
            }
            if (value > max) max = value;
        }
        System.out.println(max);
    }
}