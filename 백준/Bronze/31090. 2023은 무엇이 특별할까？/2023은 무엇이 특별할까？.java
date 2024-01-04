import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int T = readInt();
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = readInt();
            if ((N + 1) % (N % 100) == 0) {
                print.append("Good\n");
            } else print.append("Bye\n");
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