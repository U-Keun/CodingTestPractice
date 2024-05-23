import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] row = new int[2], sizes = new int[N + 1];
        for (int i = 0; i < N; i++) {
            row = readIntegers(br.readLine());
            sizes[i] = row[0];
        }
        br.close();
        sizes[N] = row[1];
        long answer = 0;
        for (int i = 0; i < N - 1; i++) {
            answer += (long) sizes[i] * sizes[i + 1];
        }
        answer *= sizes[N];
        System.out.println(answer);
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}