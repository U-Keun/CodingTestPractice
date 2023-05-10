import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[1000000];
        int a, b, c;
        for (int i = 0; i < N; i++) {
            a = ((i + 1) % 3 == 0)? (i + 1) / 3: i;
            b = ((i + 1) % 2 == 0)? (i + 1) / 2: i;
            c = i;
            if (i == 0) continue;
            else if (i == 1 || i == 2) numbers[i] = 1;
            else {
                numbers[i] = 1 + Math.min(numbers[a - 1], Math.min(numbers[b - 1], numbers[c - 1]));
            }
        }
        System.out.println(numbers[N - 1]);
    }
}