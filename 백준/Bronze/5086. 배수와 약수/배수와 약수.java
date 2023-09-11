import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int a, b;
        StringBuilder print = new StringBuilder();
        while ((a = readInt()) != 0 && (b = readInt()) != 0) {
            if (b % a == 0) print.append("factor");
            else if (a % b == 0) print.append("multiple");
            else print.append("neither");
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