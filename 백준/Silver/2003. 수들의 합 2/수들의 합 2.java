import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = readInt();
        }
        int s = 0, sum = 0, count = 0;
        for (int end = 0; end < N; end++) {
            sum += numbers[end];
            while (sum > M) {
                sum -= numbers[s++];
            }
            if (sum == M) {
                count++;
                sum -= numbers[s++];
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