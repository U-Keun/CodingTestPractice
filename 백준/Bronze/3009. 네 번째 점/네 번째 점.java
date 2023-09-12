import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int x1 = readInt(), y1 = readInt(), x2 = readInt(), y2 = readInt(), x3 = readInt(), y3 = readInt(), x, y;
        if (x1 == x2) {
            x = x3;
            if (y1 == y3) y = y2;
            else y = y1;
        } else if (y1 == y2) {
            y = y3;
            if (x1 == x3) x = x2;
            else x = x1;
        } else {
            if (x1 == x3) {
                x = x2;
                y = y1;
            } else {
                x = x1;
                y = y2;
            }
        }
        System.out.println(x + " " + y);
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