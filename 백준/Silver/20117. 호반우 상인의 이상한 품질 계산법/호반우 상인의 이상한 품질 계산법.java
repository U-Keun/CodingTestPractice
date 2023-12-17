import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = readInt();
        }
        Arrays.sort(numbers);
        int answer = 0;
        if (N % 2 == 0) {
            for (int i = N / 2; i < N; i++) {
                answer += numbers[i] * 2;
            }
        } else {
            answer += numbers[N / 2];
            for (int i = N / 2 + 1; i < N; i++) {
                answer += numbers[i] * 2;
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