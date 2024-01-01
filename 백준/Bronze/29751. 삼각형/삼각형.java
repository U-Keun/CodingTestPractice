import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int W = readInt(), H = readInt();
        System.out.printf("%.1f", W * H / 2f);
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