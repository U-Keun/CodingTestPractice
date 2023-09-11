import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int T = readInt();
        int[] coins = {25, 10, 5, 1};
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int value = readInt();
            for (int j = 0; j < 4; j++) {
                print.append(value / coins[j]).append(" ");
                value %= coins[j];
            }
            print.append('\n');
        }
        System.out.println(print);
    }
    public static int readInt() throws IOException {
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