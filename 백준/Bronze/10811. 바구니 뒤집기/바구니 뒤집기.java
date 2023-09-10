import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        int[] boxes = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            boxes[i] = i;
        }
        for (int i = 0; i < M; i++) {
            int a = readInt(), b = readInt(), idx = 0;
            int[] copy = Arrays.copyOfRange(boxes, a, b + 1);
            for (int j = b; j >= a; j--) {
                boxes[j] = copy[idx++];
            }
        }
        StringBuilder print = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            print.append(boxes[i]).append(" ");
        }
        System.out.println(print);
    }
    public static int readInt() throws IOException {
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