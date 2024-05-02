import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = readIntegers(br.readLine());
        int[][] adjMatrix = new int[NM[0]][NM[0]];
        for (int i = 0; i < NM[0]; i++) {
            adjMatrix[i] = readIntegers(br.readLine());
            for (int j = 0; j < NM[0]; j++) {
                if (i != j && adjMatrix[i][j] == 0) {
                    adjMatrix[i][j] = 1_000_000_001;
                }
            }
        }
        for (int i = 0; i < NM[0]; i++) {
            for (int j = 0; j < NM[0]; j++) {
                for (int k = 0; k < NM[0]; k++) {
                    adjMatrix[j][k] = Math.min(adjMatrix[j][k], adjMatrix[j][i] + adjMatrix[i][k]);
                }
            }
        }
        int[] input;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < NM[1]; i++) {
            input = readIntegers(br.readLine());
            if (adjMatrix[input[0] - 1][input[1] - 1] <= input[2]) {
                print.append("Enjoy other party\n");
            } else print.append("Stay here\n");
        }
        br.close();
        System.out.println(print);
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}