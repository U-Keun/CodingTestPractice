import java.io.IOException;

public class Main {
    static int[] root;
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        root = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            root[i] = i;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = readInt();
                if (i <= j) continue;
                if (k == 1) {
                    union(i + 1, j + 1);
                }
            }
        }
        int r = find(readInt());
        for (int i = 0; i < M - 1; i++) {
            int l = readInt();
            if (r != find(l)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag)
            c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag)
            return -val;
        return val;
    }
    public static int find(int x) {
        if (root[x] == x) return x;
        return root[x] = find(root[x]);
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        if (x < y) root[y] = x;
        else root[x] = y;
    }
}