import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] NMK;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        NMK = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();
        int markedRow = NMK[2] != 0 ? (NMK[2] - 1) / NMK[1] : 0;
        int markedCol = NMK[2] != 0 ? (NMK[2] - 1) % NMK[1] : 0;

        int[][] record = new int[NMK[0]][NMK[1]];
        record[0][0] = 1;
        for (int i = 0; i <= markedRow; i++) {
            for (int j = 0; j <= markedCol; j++) {
                if (i == markedRow && j == markedCol) continue;
                if (isInGrid(i + 1, j)) record[i + 1][j] += record[i][j];
                if (isInGrid(i, j + 1)) record[i][j + 1] += record[i][j];
            }
        }
        int answer = record[markedRow][markedCol];
        record[markedRow][markedCol] = 1;
        for (int i = markedRow; i < NMK[0]; i++) {
            for (int j = markedCol; j < NMK[1]; j++) {
                if (isInGrid(i + 1, j)) record[i + 1][j] += record[i][j];
                if (isInGrid(i, j + 1)) record[i][j + 1] += record[i][j];
            }
        }
        answer *= record[NMK[0] - 1][NMK[1] - 1];
        System.out.println(answer);
    }
    private static boolean isInGrid(int row, int col) {
        return row >= 0 && row < NMK[0]
                && col >= 0 && col < NMK[1];
    }

}