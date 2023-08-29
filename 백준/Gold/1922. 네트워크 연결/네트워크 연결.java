import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    static int V, E, count = 0;
    static int[] parentRecord;
    static PriorityQueue<Edge> edges;
    static long weightSum = 0;
    public static void main(String[] args) throws IOException {
        setVariables();
        while (count < V - 1) {
            Edge tmp = edges.poll();
            if (union(tmp.v1, tmp.v2)) {
                count++;
                weightSum += tmp.weight;
            }
        }
        System.out.println(weightSum);
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
    public static void setVariables() throws IOException {
        V = readInt();
        E = readInt();
        parentRecord = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            parentRecord[i] = i;
        }
        edges = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            int A = readInt(), B = readInt(), C = readInt();
            edges.add(new Edge(A, B, C));
        }
    }
    public static int find(int x) {
        if (parentRecord[x] == x) return x;
        return find(parentRecord[x]);
    }
    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (x < y) parentRecord[y] = x;
        else parentRecord[x] = y;
        return true;
    }
}
class Edge implements Comparable<Edge> {
    int v1, v2;
    int weight;
    public Edge(int v1, int v2, int weight) {
        this.v1 = Math.min(v1, v2);
        this.v2 = Math.max(v1, v2);
        this.weight = weight;
    }
    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}