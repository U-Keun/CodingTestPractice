import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[] numbers = new int[n + 1];
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int k = readInt();
            if (i == 1) numbers[i] = k;
            else numbers[i] = Math.max(k, k + numbers[i - 1]);
            answer = Math.max(answer, numbers[i]);
        }
        System.out.println(answer);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
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