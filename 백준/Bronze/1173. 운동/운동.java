import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), m = readInt(), M = readInt(), T = readInt(), R = readInt();
        if (M - m < T) {
            System.out.println(-1);
            return;
        }
        int X = m, time = 0;
        while (N > 0) {
            if (X + T <= M) {
                X += T;
                N--;
            } else {
                X = Math.max(m, X - R);
            }
            time++;
        }
        System.out.println(time);
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