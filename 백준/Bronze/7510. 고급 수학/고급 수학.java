import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        StringBuilder print = new StringBuilder();
        String format = "Scenario #%d:\n%s\n\n";
        for (int i = 1; i <= n; i++) {
            int[] d = new int[3];
            d[0] = readInt();
            d[1] = readInt();
            d[2] = readInt();
            Arrays.sort(d);
            String answer = "no";
            if (d[0] * d[0] + d[1] * d[1] == d[2] * d[2]) answer = "yes";
            print.append(String.format(format, i, answer));
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