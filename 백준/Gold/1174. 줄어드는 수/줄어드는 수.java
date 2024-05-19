import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static List<Long> numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();
        numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            backtracking(i);
        }
        Collections.sort(numbers);
        if (N > 1023) {
            System.out.println(-1);
            return;
        }
        System.out.println(numbers.get(N - 1));
    }
    private static void backtracking(long number) {
        numbers.add(number);
        for (int i = 0; i < 10; i++) {
            if (number % 10 <= i) break;
            backtracking(number * 10 + i);
        }
    }
}