import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int A = readInt(), B = readInt(), C = readInt(), N = readInt();
        for (int i = 0; i <= N / C; i++) {
            int tmp1 = N - C * i;
            for (int j = 0; j <= tmp1 / B; j++) {
                int tmp2 = tmp1 - B * j;
                for (int k = 0; k <= tmp2 / A; k++) {
                    if (tmp2 % A == 0) {
                        System.out.println(1);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
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