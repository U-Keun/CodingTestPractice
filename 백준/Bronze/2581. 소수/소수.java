import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int M = readInt(), N = readInt(), sum = 0, min = 0;
        for (int i = M; i <= N; i++) {
            if (i == 1) continue;
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                sum += i;
                min = (min == 0) ? i:min;
            }
        }
        StringBuilder print = new StringBuilder();
        if (sum != 0) print.append(sum).append('\n').append(min);
        else print.append(-1);
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