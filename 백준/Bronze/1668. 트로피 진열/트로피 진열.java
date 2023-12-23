import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] trophies = new int[N];
        int left = 0, right = 0, leftMax = 0, rightMax = 0;
        for (int i = 0; i < N; i++) {
            trophies[i] = readInt();
            if (leftMax < trophies[i]) {
                left++;
                leftMax = trophies[i];
            }
        }
        for (int i = N- 1; i >= 0; i--) {
            if (rightMax < trophies[i]) {
                right++;
                rightMax = trophies[i];
            }
        }
        System.out.println(left);
        System.out.println(right);
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