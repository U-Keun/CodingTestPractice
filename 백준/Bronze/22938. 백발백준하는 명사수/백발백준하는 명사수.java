import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        long x1 = readInt(), y1 = readInt(), r1 = readInt(), x2 = readInt(), y2 = readInt(), r2 = readInt();
        long diffX = x1 - x2, diffY = y1 - y2;
        double distance = Math.sqrt(diffX * diffX + diffY * diffY);
        if (distance < r1 + r2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
}