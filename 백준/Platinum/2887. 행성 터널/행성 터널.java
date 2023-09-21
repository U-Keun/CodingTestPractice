import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(br.readLine());
            parent = new int[n];
            StringTokenizer st;
            Planet[] planets = new Planet[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                planets[i] = Planet.create(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
                parent[i] = i;
            }
            PriorityQueue<Tunnel> tunnels = new PriorityQueue<>();
            Arrays.sort(planets, Comparator.comparing(Planet::getX));
            for (int i = 1; i < n; i++) tunnels.add(Tunnel.create(planets[i - 1], planets[i]));
            Arrays.sort(planets, Comparator.comparing(Planet::getY));
            for (int i = 1; i < n; i++) tunnels.add(Tunnel.create(planets[i - 1], planets[i]));
            Arrays.sort(planets, Comparator.comparing(Planet::getZ));
            for (int i = 1; i < n; i++) tunnels.add(Tunnel.create(planets[i - 1], planets[i]));
            int answer = 0;
            while (n > 1) {
                Tunnel tmp = tunnels.poll();
                if (union(tmp.getStar1().getIdx(), tmp.getStar2().getIdx())) {
                    answer += tmp.getDistance();
                    n--;
                }
            }
            bw.write(String.valueOf(answer));
            bw.flush();
        }

    }
    public static int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }
    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (x < y) parent[y] = x;
        else parent[x] = y;
        return true;
    }
}
class Tunnel implements Comparable<Tunnel> {
    private final Planet star1, star2;
    private final int distance;

    private Tunnel(Planet star1, Planet star2) {
        this.star1 = star1;
        this.star2 = star2;
        this.distance = Math.min(Math.abs(star1.getX()- star2.getX()),
                Math.min(Math.abs(star1.getY() - star2.getY()), Math.abs(star1.getZ() - star2.getZ())));
    }
    public static Tunnel create(Planet star1, Planet star2) {
        return new Tunnel(star1, star2);
    }
    public Planet getStar1() { return star1; }
    public Planet getStar2() { return star2; }
    public int getDistance() { return distance; }
    @Override
    public int compareTo(Tunnel other) {
        return this.distance - other.distance;
    }
}
class Planet {
    private final int x, y, z, idx;
    private Planet(int x, int y, int z, int idx) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.idx = idx;
    }
    public static Planet create(int x, int y, int z, int idx) { return new Planet(x, y, z, idx); }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
    public int getIdx() { return idx; }
}