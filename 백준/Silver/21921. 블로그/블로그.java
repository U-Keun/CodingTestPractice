import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), X = readInt();
        int[] visitors = new int[N];
        for (int i = 0; i < N; i++) {
            visitors[i] = readInt();
        }
        long max = 0;
        for (int i = 0; i < X; i++) {
            max += visitors[i];
        }
        int s = 0, e = X, count = 1;
        long current = max;
        while (e < N) {
            current = current - visitors[s++] + visitors[e++];
            if (current > max) {
                max = current;
                count = 1;
            } else if (current == max) {
                count++;
            }
        }
        if (max == 0) {
            System.out.println("SAD");
            return;
        }
        System.out.println(max);
        System.out.println(count);

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