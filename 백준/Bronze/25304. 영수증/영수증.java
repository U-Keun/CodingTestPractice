import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int X = readInt(), N = readInt();
        for (int i = 0; i < N; i++) {
            int a = readInt(), b = readInt();
            X -= a * b;
        }
        if (X == 0) System.out.println("Yes");
        else System.out.println("No");
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