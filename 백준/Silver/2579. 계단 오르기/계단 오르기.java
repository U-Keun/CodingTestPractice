import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stepScores = new int[N];
        for (int i = 0; i < N; i++) {
            stepScores[i] = Integer.parseInt(br.readLine());
        }
        int[][] record = new int[2][N];
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                record[0][i] = stepScores[i];
                record[1][i] = stepScores[i];
            } else if (i == 1) {
                record[0][i] = stepScores[i];
                record[1][i] = record[0][i - 1] + stepScores[i];
            } else {
                record[0][i] = Math.max(record[0][i - 2], record[1][i - 2]) + stepScores[i];
                record[1][i] = record[0][i - 1] + stepScores[i];
            }
        }
        System.out.println(Math.max(record[0][N - 1], record[1][N - 1]));
    }
}