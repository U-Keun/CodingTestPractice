import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] numbers = new int[5];
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            numbers[i] = readInt();
            sum += numbers[i];
        }
        Arrays.sort(numbers);
        System.out.println(sum / 5);
        System.out.println(numbers[2]);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') c = System.in.read();
        boolean flag = false;
        if (c == '-') {
            flag = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (flag) return -val;
        return val;
    }
}