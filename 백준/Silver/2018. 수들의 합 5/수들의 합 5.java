import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int s = 1, e = 1, answer = 1;
        long sum = 0;
        while (e <= N) {
            if (sum == N) {
                answer++;
            }
            if (sum <= N) {
                sum += e++;
            } else {
                sum -= s++;
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