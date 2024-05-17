import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(rev(rev(numbers[0]) + rev(numbers[1])));
    }
    private static int rev(int number) {
        int answer = 0;
        while (number > 0) {
            answer *= 10;
            answer += number % 10;
            number /= 10;
        }
        return answer;
    }
}