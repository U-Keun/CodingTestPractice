import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean[] submitted = new boolean[31];
        for (int i = 0; i < 28; i++) {
            int k = readInt();
            submitted[k] = true;
        }
        StringBuilder print = new StringBuilder();
        for (int i = 1; i <= 30; i++) {
            if (!submitted[i]) print.append(i).append('\n');
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