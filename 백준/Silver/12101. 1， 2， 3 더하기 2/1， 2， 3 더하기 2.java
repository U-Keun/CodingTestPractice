import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int count;
    private static String answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();
        count = input[1];
        backtracking(input[0], "");
        if (answer == null) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
    private static void backtracking(int value, String equation) {
        if (value < 0 || count == 0) return;
        if (value == 0) {
            count--;
            if (count == 0) {
                answer = equation;
            }
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (equation.isEmpty()) {
                backtracking(value - i, equation + i);
            } else {
                backtracking(value - i, equation + "+" + i);
            }
        }
    }
}