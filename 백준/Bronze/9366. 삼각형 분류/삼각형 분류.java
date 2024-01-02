import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        StringBuilder print = new StringBuilder();
        String format = "Case #%d: %s\n";
        for (int i = 1; i <= n; i++) {
            int[] d = new int[3];
            d[0] = readInt();
            d[1] = readInt();
            d[2] = readInt();
            Arrays.sort(d);
            if (d[0] + d[1] <= d[2]) {
                print.append(String.format(format, i, "invalid!"));
                continue;
            }
            if (d[0] == d[1] && d[1] == d[2]) {
                print.append(String.format(format, i, "equilateral"));
            } else if (d[0] == d[1] || d[0] == d[2] || d[1] == d[2]) {
                print.append(String.format(format, i, "isosceles"));
            } else print.append(String.format(format, i, "scalene"));
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