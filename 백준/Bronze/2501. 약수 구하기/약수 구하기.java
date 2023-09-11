import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), K = readInt(), answer = 0;
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) K--;
            if (K == 0) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
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