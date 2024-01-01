import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int r = readInt();
        System.out.printf("%.6f\n", r * r * 3.14159265358979d);
        System.out.printf("%.6f", r * r * 2d);
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