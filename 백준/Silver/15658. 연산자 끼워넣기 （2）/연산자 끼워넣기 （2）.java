import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N, min, max;
    private static int[] numbers, operations;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = readIntegers(br.readLine());
        operations = readIntegers(br.readLine());
        br.close();

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        backtracking(1, numbers[0]);
        System.out.println(max);
        System.out.println(min);
    }
    private static void backtracking(int depth, int value) {
        if (depth == N) {
            min = Math.min(min, value);
            max = Math.max(max, value);
            return;
        }
        int tmp = value;
        if (operations[0] > 0) {
            operations[0]--;
            backtracking(depth + 1, tmp + numbers[depth]);
            operations[0]++;
        }
        if (operations[1] > 0) {
            operations[1]--;
            backtracking(depth + 1, tmp - numbers[depth]);
            operations[1]++;
        }if (operations[2] > 0) {
            operations[2]--;
            backtracking(depth + 1, tmp * numbers[depth]);
            operations[2]++;
        }if (operations[3] > 0) {
            operations[3]--;
            backtracking(depth + 1, tmp / numbers[depth]);
            operations[3]++;
        }


    }

    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}