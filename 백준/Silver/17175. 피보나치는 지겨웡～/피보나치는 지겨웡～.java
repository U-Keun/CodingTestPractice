import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        if (n == 0) {
            System.out.println(1);
            return;
        }
        long[] counts = new long[n + 1];
        counts[0] = 1;
        counts[1] = 1;
        for (int i = 2; i <= n; i++) {
            counts[i] = counts[i - 1] + counts[i - 2] + 1;
            counts[i] %= 1000000007;
        }
        System.out.println(counts[n]);
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