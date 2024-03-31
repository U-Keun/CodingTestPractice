import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final String YES = "Yes\n", NO = "No\n";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = readIntegers(br.readLine());
        StringBuilder print = new StringBuilder();
        while (input[0] != 0 || input[1] != 0) {
            if (input[0] > input[1]) print.append(YES);
            else print.append(NO);
            input = readIntegers(br.readLine());
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