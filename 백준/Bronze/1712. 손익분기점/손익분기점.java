import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int a = readInt(), b = readInt(), c= readInt();
        if (c <= b) {
            System.out.println(-1);
        } else {
            System.out.println(a / (c - b) + 1);
        }
    }
    private static int readInt() throws IOException {
        int c, n;
        boolean isNegative = false;
        c = System.in.read();
        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }
        n = c & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return isNegative ? -n : n;
    }
}