import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int a = readInt(), b = readInt();
        float PI = 3.141592f;
        System.out.printf("%.6f", 2 * (PI * b + a));
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