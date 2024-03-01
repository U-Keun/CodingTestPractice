import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        br.close();
        int n = input.length();
        int[] numbers = input.chars()
                .map(c -> c - '0')
                .toArray();
        if (n == 0 || numbers[0] == 0) {
            System.out.println(0);
            return;
        }
        long prev = 1, curr = 1;
        for (int i = 1; i < n; i++) {
            long temp = 0;
            if (numbers[i] != 0) {
                temp += curr;
            }
            int twoDigit = numbers[i - 1] * 10 + numbers[i];
            if (twoDigit >= 10 && twoDigit <= 26) {
                temp += prev;
            }
            prev = curr;
            curr = temp % 1000000;
        }
        System.out.println(curr);
    }
}