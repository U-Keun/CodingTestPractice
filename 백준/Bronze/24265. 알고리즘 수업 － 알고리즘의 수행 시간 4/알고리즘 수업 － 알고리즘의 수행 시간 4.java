import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        System.out.println((long) n * (n - 1) / 2);
        System.out.println(2);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
}