import java.io.IOException;

public class Main {
    private static int even = 1, odd = 1;
    public static void main(String[] args) throws IOException {
        int a = readInt(), b = readInt(), c = readInt();
        multiply(a);
        multiply(b);
        multiply(c);
        if (a == 1 || b == 1 || c == 1) {
            System.out.println(odd);
        } else if (odd == 1) {
            System.out.println(even);
        } else {
            System.out.println(odd);
        }
    }
    private static void multiply(int number) {
        if (number % 2 == 0) {
            even *= number;
        } else {
            odd *= number;
        }
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