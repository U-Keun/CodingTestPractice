import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int X = readInt(), k = 1;
        while (X > k) X -= k++;
        StringBuilder print = new StringBuilder();
        if (k % 2 == 1) print.append((k - X + 1) + "/" + X);
        else print.append(X + "/" + (k - X + 1));
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