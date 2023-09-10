import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] record = {1, 1, 2, 2, 2, 8};
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            record[i] -= readInt();
            print.append(record[i] + " ");
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