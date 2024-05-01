import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = readIntegers(br.readLine());
        int[] array = readIntegers(br.readLine());
        Arrays.sort(array);
        System.out.println(array[NM[1] - 1]);
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}