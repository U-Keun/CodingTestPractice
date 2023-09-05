import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static StringBuilder print = new StringBuilder();
    static int T, N;
    static ArrayList<Base> bases;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        T = readInt();
        for (int i = 0; i < T; i++) {
            N = readInt();
            parent = new int[N + 1];
            bases = new ArrayList<>();
            for (int j = 1; j <= N; j++) {
                parent[j] = j;
                int x = readInt(), y = readInt(), R = readInt();
                Base tmp = new Base(x, y, R);
                for (int k = 0; k < bases.size(); k++) {
                    if (distance(tmp, bases.get(k)) <= tmp.radius + bases.get(k).radius) {
                        union(k + 1, j);
                    }
                }
                bases.add(tmp);
            }
            Set<Integer> rep = new HashSet<>();
            for (int j = 1; j <= N; j++) {
                rep.add(find(parent[j]));
            }
            print.append(rep.size()).append('\n');
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
    public static double distance(Base A, Base B) {
        int xDiff = A.x - B.x, yDiff = A.y - B.y;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
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