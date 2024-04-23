import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] record = new int[N + 1][6];
        int[] input;
        int answer = 0, grade = 6;
        for (int i = 1; i <= N; i++) {
            input = readIntegers(br.readLine());
            record[i][input[0]] = record[i - 1][input[0]] + 1;
            record[i][input[1]] = record[i - 1][input[1]] + 1;
            if (answer < record[i][input[0]]) {
                answer = record[i][input[0]];
                grade = input[0];
            } else if (answer == record[i][input[0]]) {
                if (grade > input[0]) {
                    grade = input[0];
                }
            }
            if (answer < record[i][input[1]]) {
                answer = record[i][input[1]];
                grade = input[1];
            } else if (answer == record[i][input[1]]) {
                if (grade > input[1]) {
                    grade = input[1];
                }
            }
        }
        br.close();
        System.out.println(answer + " " + grade);

    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}