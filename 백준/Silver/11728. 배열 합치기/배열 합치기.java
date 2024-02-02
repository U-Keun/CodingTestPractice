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
        int c, n;
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