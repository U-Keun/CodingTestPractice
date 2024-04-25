import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static List<Long> decreasingNumbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();
        if (N > 1022) {
            System.out.println(-1);
            return;
        }
        decreasingNumbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            createDecreasingNumber(i, i);
        }
        Collections.sort(decreasingNumbers);
        System.out.println(decreasingNumbers.get(N));
    }

    private static void createDecreasingNumber(long number, int lastDigit) {
        if (number > 9876543210L) {
            return;
        }
        decreasingNumbers.add(number);
        for (int i = 0; i < lastDigit; i++) {
            createDecreasingNumber(number * 10 + i, i);
        }
    }
}