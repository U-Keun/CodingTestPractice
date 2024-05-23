import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] row;
        long answer = 0;
        for (int i = 0; i < N; i++) {
            row = readIntegers(br.readLine());
            if (i < N - 1) {
                answer += (long) row[0] * row[1];
                continue;
            }
            answer *= row[1];
        }
        br.close();
        System.out.println(answer);
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}