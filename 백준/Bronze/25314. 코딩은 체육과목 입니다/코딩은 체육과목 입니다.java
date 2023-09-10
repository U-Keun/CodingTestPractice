import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt() / 4;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N; i++) {
            print.append("long ");
        }
        print.append("int");
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