import java.io.IOException;

public class Main {
    private static int N, K;
    public static void main(String[] args) throws IOException {
        N = readInt();
        K = readInt();
        int low = 1, high = K;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (isBeforeKth(mid)) low = mid + 1;
            else high = mid;
        }
        System.out.println(low);
    }
    private static boolean isBeforeKth(int mid) {
        int value = 0;
        for (int i = 1; i <= N; i++) {
            value += Math.min(N, mid / i);
        }
        if (value < K) return true;
        return false;
    }
    private static int readInt() throws IOException {
        int c, n = 0;
        boolean isNegative = false;
        c = System.in.read();
        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }
        n = c & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return isNegative ? -n : n;
    }
}