import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        int N;
        StringBuilder print = new StringBuilder();
        while ((N = readInt()) != -1) {
            int value = 0;
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 1; i * 2 <= N; i++) {
                if (N % i == 0) {
                    value += i;
                    numbers.add(i);
                }
            }
            if (N == value) {
                print.append(N + " = ");
                for (int i = 0; i < numbers.size(); i++) {
                    print.append(numbers.get(i));
                    if (i != numbers.size() - 1) print.append(" + ");
                }
            } else print.append(N + " is NOT perfect.");
            print.append('\n');
        }
        System.out.println(print);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') c = System.in.read();
        boolean flag = (c == '-');
        if (flag) c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (flag) return -val;
        return val;
    }
}