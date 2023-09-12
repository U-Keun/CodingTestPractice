import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE,
                yMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int x = readInt(), y = readInt();
            xMin = Math.min(xMin, x);
            xMax = Math.max(xMax, x);
            yMin = Math.min(yMin, y);
            yMax = Math.max(yMax, y);
        }
        long area = (long) (xMax - xMin) * (yMax - yMin);
        System.out.println(area);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') c = System.in.read();
        boolean flag = (c == '-');
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (flag) return -val;
        return val;
    }
}