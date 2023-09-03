import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static StringBuilder print = new StringBuilder();
    static int n, m, caseIdx = 1;
    static Set<Integer> trees = new HashSet<>();
    static int[] parent, rank;
    public static void main(String[] args) throws IOException {
        while (true) {
            n = readInt();
            m = readInt();
            if (n == 0 && m == 0) break;
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
                trees.add(i);
            }
            for (int i = 0; i < m; i++) {
                int a = readInt(), b = readInt();
                union(a, b);
            }
            if (trees.size() == 0) print.append("Case " + caseIdx + ": No trees.");
            else if (trees.size() == 1) print.append("Case " + caseIdx + ": There is one tree.");
            else print.append("Case " + caseIdx + ": A forest of " + trees.size() + " trees.");
            print.append('\n');
            caseIdx++;
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
    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            trees.remove(y);
            return false;
        }
        if (rank[x] == rank[y]) {
            rank[x]++;
        }
        if (rank[x] > rank[y]) {
            trees.remove(y);
            parent[y] = x;
        } else {
            trees.remove(x);
            parent[x] = y;
        }
        return true;
    }
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}