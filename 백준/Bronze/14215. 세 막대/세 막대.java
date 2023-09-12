import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int a = readInt(), b = readInt(), c = readInt();
        int[] array = {a, b, c};
        Arrays.sort(array);
        while (array[0] + array[1] <= array[2]) array[2]--;
        System.out.println(array[0] + array[1] + array[2]);
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