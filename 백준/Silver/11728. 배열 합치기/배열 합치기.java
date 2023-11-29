import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt(), m = readInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = readInt();
        }
        int[] B = new int[m];
        for (int i = 0; i < m; i++) {
            B[i] = readInt();
        }
        int a = 0, b = 0;
        StringBuilder print = new StringBuilder();
        while (a < n && b < m) {
            if (A[a] <= B[b]) print.append(A[a++]);
            else print.append(B[b++]);
            print.append(" ");
        }
        if (a < n) {
            while (a < n) print.append(A[a++]).append(" ");
        }
        if (b < m) {
            while (b < m) print.append(B[b++]).append(" ");
        }
        System.out.println(print);
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