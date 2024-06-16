import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dice = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] numbers = new int[3];
        numbers[0] = Math.min(dice[0], dice[5]);
        numbers[1] = Math.min(dice[1], dice[4]);
        numbers[2] = Math.min(dice[2], dice[3]);
        Arrays.sort(numbers);

        if (N == 1) {
            Arrays.sort(dice);
            System.out.println(Arrays.stream(dice).sum() - dice[5]);
            return;
        }
        long answer = 0;
        answer += (long) (N - 2) * (N - 2) * numbers[0] * 6;
        answer -= (long) (N - 2) * (N - 2) * numbers[0];
        answer += (long) (N - 2) * (numbers[0] + numbers[1]) * 12;
        answer -= (long) (N - 2) * numbers[1] * 4;
        answer += (long) 8 * (numbers[0] + numbers[1] + numbers[2]);
        answer -= (long) 4 * numbers[2];
        System.out.println(answer);
    }
}