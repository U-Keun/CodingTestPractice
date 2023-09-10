import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), l = 2 * N - 1, blank = N - 1;
        StringBuilder print = new StringBuilder();
        for (int i = 1; i <= l; i++) {
            print.append(" ".repeat(blank));
            if (i < N) {
                print.append("*".repeat(2 * i - 1));
                blank--;
            } else {
                print.append("*".repeat(4 * N - 2 * i - 1));
                blank++;
            }
            print.append('\n');
        }
        System.out.println(print);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
}