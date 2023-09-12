import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int a = readInt(), b = readInt(), c = readInt(),
                d = readInt(), e = readInt(), f = readInt();
        for (int i = -999; i < 1000; i++) {
            if (b != 0) {
                if ((c - a * i) % b != 0) continue;
                if (d * i + ((c - a * i) / b) * e == f) {
                    System.out.println(i + " " + ((c - a * i) / b));
                    return;
                }
            } else {
                if (a * i != c) continue;
                else {
                    System.out.println(i + " " + ((f - d * i) / e));
                    return;
                }
            }

        }
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