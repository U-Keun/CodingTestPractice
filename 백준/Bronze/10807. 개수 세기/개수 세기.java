import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = readInt();
        }
        int target = readInt(), count = 0;
        for (int i:numbers) {
            if (target == i) count++;
        }
        System.out.println(count);
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