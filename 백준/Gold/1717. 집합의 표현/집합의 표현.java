import java.io.IOException;

public class Main {
    static StringBuilder print = new StringBuilder();
    static int[] parentRecord;
    public static void main(String[] args) throws IOException {
        int n = readInt(), m = readInt();
        parentRecord = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parentRecord[i] = i;
        }
        int o, a, b;
        for (int i = 0; i < m; i++) {
            o = readInt();
            a = readInt();
            b = readInt();
            if (o == 0) {
                union(a, b);
            } else {
                if (find(a) == find(b)) {
                    print.append("YES").append('\n');
                } else print.append("NO").append('\n');
            }
        }
        System.out.println(print);
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
        if (parentRecord[x] == x) return x;
        return parentRecord[x] = find(parentRecord[x]);
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        if (x < y) parentRecord[y] = x;
        else parentRecord[x] = y;
    }
}