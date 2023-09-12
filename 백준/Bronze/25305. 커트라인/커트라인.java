import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), k = readInt();
        Integer[] scores = new Integer[N];
        for (int i = 0; i < N; i++) {
            scores[i] = readInt();
        }
        Arrays.sort(scores, Collections.reverseOrder());
        System.out.println(scores[k - 1]);
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