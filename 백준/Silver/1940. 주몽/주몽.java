import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = readInt();
        }
        Arrays.sort(numbers);
        int s = 0, e = N - 1, answer = 0;
        while (s < e) {
            int sum = numbers[s] + numbers[e];
            if (sum < M) s++;
            else if (sum > M) e--;
            else {
                s++;
                e--;
                answer++;
            }
        }
        System.out.println(answer);
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
}