import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int D = readInt(), H = readInt(), W = readInt();
        double ratioSquare = (double) D * D / (double) (H * H + W * W);
        int h = (int) (H * Math.sqrt(ratioSquare));
        int w = (int) (W * Math.sqrt(ratioSquare));
        System.out.printf("%d %d", h, w);
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