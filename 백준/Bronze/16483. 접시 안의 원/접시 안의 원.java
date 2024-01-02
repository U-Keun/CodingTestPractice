import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double T = readInt();
        double value = T / 2d;
        System.out.println(Math.round(value * value));
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