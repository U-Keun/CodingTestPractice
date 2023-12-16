import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            numbers[i] = numbers[i - 1] + readInt();
        }
        long answer = 0;
        for (int i = 1; i < N; i++) {
            answer += (long) (numbers[N] - numbers[i]) * (numbers[i] - numbers[i - 1]);
        }
        System.out.println(answer);
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
}