import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        long W = readInt(), H = readInt(), N = readInt();
        double answer = 0;
        if (N % 2 == 0) {
            answer = (double) (N - 2) / 4;
        } else {
            answer = (double) (N - 1) * (N - 1) / (4 * N);
        }
        answer *= 2 * H;
        System.out.printf("%.6f", answer);
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