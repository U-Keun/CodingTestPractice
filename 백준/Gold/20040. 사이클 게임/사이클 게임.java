import java.io.IOException;

public class Main {
    static int N, M, count = 0;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        boolean terminated = false;
        for (int i = 0; i < M; i++) {
            int a = readInt(), b = readInt();
            if (!terminated) {
                count++;
                if (!union(a, b)) {
                    terminated = true;
                }
            }
        }
        if (terminated) System.out.println(count);
        else System.out.println(0);
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
    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
        return true;
    }
    public static int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }

}