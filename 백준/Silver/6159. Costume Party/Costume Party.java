import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), S = readInt();
        int[] cows = new int[N];
        for (int i = 0; i < N; i++) {
            cows[i] = readInt();
        }
        Arrays.sort(cows);
        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (cows[i] + cows[j] <= S) {
                    count++;
                } else break;
            }
        }
        System.out.println(count);
    }
    private static int readInt() throws IOException {
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