import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int K = readInt(), d1 = readInt(), d2 = readInt();
        double d = (d1 - d2) / 2d;
        System.out.println(K * K - d * d);
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