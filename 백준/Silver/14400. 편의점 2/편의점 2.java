import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] x = new int[N], y = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = readInt();
            y[i] = readInt();
        }
        System.out.println(sumDeviation(x) + sumDeviation(y));
    }
    private static long sumDeviation(int[] numbers) {
        int n = numbers.length;
        Arrays.sort(numbers);
        int median = numbers[n / 2];
        long sum = 0;
        for (int number : numbers) {
            sum += Math.abs(median - number);
        }
        return sum;
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
}