import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            numbers.add(readInt());
        }
        StringBuilder print = new StringBuilder();
        while (!numbers.isEmpty()) {
            print.append(numbers.poll()).append('\n');
        }
        System.out.println(print);
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