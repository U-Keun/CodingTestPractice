import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static StringBuilder print = new StringBuilder();
    static int T, N, answer;
    static ArrayList<Base> bases;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        T = readInt();
        for (int i = 0; i < T; i++) {
            N = readInt();
            answer = N;
            parent = new int[N + 1];
            bases = new ArrayList<>();
            for (int j = 1; j <= N; j++) {
                parent[j] = j;
                int x = readInt(), y = readInt(), R = readInt();
                Base tmp = new Base(x, y, R);
                for (int k = 0; k < bases.size(); k++) {
                    int xDiff = tmp.x - bases.get(k).x;
                    int yDiff = tmp.y - bases.get(k).y;
                    int r = tmp.radius + bases.get(k).radius;
                    if (xDiff * xDiff + yDiff * yDiff <= r * r) {
                        if (union(k + 1, j)) answer--;
                    }
                }
                bases.add(tmp);
            }
            print.append(answer).append('\n');
        }
        System.out.println(print);
    }
    public static int readInt() throws IOException {
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
    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (x > y) {
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
class Base {
    int x, y, radius;
    public Base(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}