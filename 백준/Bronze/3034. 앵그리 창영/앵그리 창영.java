import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), W = readInt(), H = readInt();
        int standard = W * W + H * H;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int k = readInt();
            if (k * k <= standard) {
                print.append("DA");
            } else
                print.append("NE");
            print.append("\n");
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