import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder print = new StringBuilder();
        String format1 = "Triangle #%d\n%s = %.3f\n\n";
        String format2 = "Triangle #%d\nImpossible.\n\n";
        int index = 1;
        while (true) {
            int a = readInt(), b = readInt(), c = readInt();
            if (a == 0 && b == 0 && c == 0) break;
            if (a == -1) {
                int value = c * c - b * b;
                if (value <= 0) {
                    print.append(String.format(format2, index++));
                } else print.append(String.format(format1, index++, "a", Math.sqrt(value)));
            } else if (b == -1) {
                int value = c * c - a * a;
                if (value <= 0) {
                    print.append(String.format(format2, index++));
                } else print.append(String.format(format1, index++, "b", Math.sqrt(value)));
            } else {
                int value = a * a + b * b;
                print.append(String.format(format1, index++, "c", Math.sqrt(value)));
            }
        }
        System.out.println(print);
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