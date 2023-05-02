import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int[] numbers = new int[2];
        String reverse;
        for (int i = 0; i < 2; i++) {
            reverse = "";
            for (int j = input[i].length(); j >= 1; j--) {
                reverse += input[i].charAt(j - 1);
            }
            numbers[i] = Integer.parseInt(reverse);
        }

        System.out.println(Math.max(numbers[0], numbers[1]));
    }
}