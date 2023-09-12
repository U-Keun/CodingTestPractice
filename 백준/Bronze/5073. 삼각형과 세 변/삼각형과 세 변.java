import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int a, b, c;
        StringBuilder print = new StringBuilder();
        while ((a = readInt()) != 0 && (b = readInt()) != 0 && (c = readInt()) != 0) {
            int[] array = {a, b, c};
            Arrays.sort(array);
            if (array[0] + array[1] <= array[2]) print.append("Invalid");
            else if (a == b && b == c) print.append("Equilateral");
            else if (a == b || b == c || a == c) print.append("Isosceles");
            else print.append("Scalene");
            print.append('\n');
        }
        System.out.println(print);
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