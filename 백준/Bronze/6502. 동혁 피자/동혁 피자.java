import java.io.IOException;

public class Main {
    private static final String POSITIVE_MESSAGE = "Pizza %d fits on the table.\n";
    private static final String NEGATIVE_MESSAGE = "Pizza %d does not fit on the table.\n";
    public static void main(String[] args) throws IOException {
        StringBuilder print = new StringBuilder();
        int index = 1;
        while (true) {
            int r = readInt();
            if (r == 0) break;
            int w = readInt(), l = readInt();
            double d = Math.sqrt(w * w + l * l);
            if (d <= 2 * r) print.append(String.format(POSITIVE_MESSAGE, index++));
            else print.append(String.format(NEGATIVE_MESSAGE, index++));
        }
        System.out.println(print);
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