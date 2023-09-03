import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int N, M, K, answer = 0;
    static int[] parent, costs;
    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        K = readInt();
        parent = new int[N + 1];
        costs = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            costs[i] = readInt();
        }
        for (int i = 0; i < M; i++) {
            int v = readInt(), w = readInt();
            union(v, w);
        }
        Set<Integer> roots = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            roots.add(find(parent[i]));
        }
        for (Integer l:roots) {
            K -= costs[l];
            answer += costs[l];
        }
        if (K < 0) System.out.println("Oh no");
        else System.out.println(answer);
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
            return false;
        }
        if (costs[x] > costs[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
        return true;
    }
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}