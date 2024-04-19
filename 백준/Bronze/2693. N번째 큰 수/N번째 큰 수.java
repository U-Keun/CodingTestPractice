import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] numbers;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            numbers = readIntegers(br.readLine());
            Arrays.sort(numbers);
            print.append(numbers[7]).append("\n");
        }
        System.out.println(print);
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}