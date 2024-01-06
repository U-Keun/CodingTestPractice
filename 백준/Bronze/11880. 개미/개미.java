import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int T = readInt();
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int a = readInt(), b = readInt(), c = readInt();
            long d1 = (long) a * a + (long) (b + c) * (b + c);
            long d2 = (long) b * b + (long) (a + c) * (a + c);
            long d3 = (long) c * c + (long) (a + b) * (a + b);
            print.append(String.format("%d\n", Math.min(d1, Math.min(d2, d3))));
        }
        System.out.println(print);
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