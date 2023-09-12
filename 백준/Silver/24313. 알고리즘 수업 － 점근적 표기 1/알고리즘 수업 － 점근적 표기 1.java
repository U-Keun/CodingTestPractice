import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int a1 = readInt(), a0 = readInt(), c = readInt(), n0 = readInt();
        if (a1 * n0 + a0 <= c * n0 && a1 <= c) System.out.println(1);
        else System.out.println(0);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') c = System.in.read();
        boolean flag = false;
        if (c == '-') {
            flag = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (flag) return -val;
        return val;
    }
}