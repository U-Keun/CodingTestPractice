import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int a = readInt(), b = readInt(), c = readInt();
        if (a + b + c != 180) System.out.println("Error");
        else if (a == b && b == c) System.out.println("Equilateral");
        else if (a == b || b == c || a == c) System.out.println("Isosceles");
        else System.out.println("Scalene");
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
}