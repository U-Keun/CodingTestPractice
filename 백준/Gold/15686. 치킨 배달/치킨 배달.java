import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int N, M, houseCount, chickenCount, min = Integer.MAX_VALUE;
    private static int[][] distances;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        List<Point> houses = new ArrayList<>(), chickens = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = readInt();
                if (k == 1) {
                    houses.add(new Point(i, j));
                } else if (k == 2) {
                    chickens.add(new Point(i, j));
                }
            }
        }
        houseCount = houses.size();
        chickenCount = chickens.size();
        distances = new int[houseCount][chickenCount];
        for (int i = 0; i < houseCount; i++) {
            for (int j = 0; j < chickenCount; j++) {
                distances[i][j] = Math.abs(houses.get(i).x - chickens.get(j).x) + Math.abs(houses.get(i).y - chickens.get(j).y);
            }
        }
        visited = new boolean[chickenCount];
        recursion(0, 0);
        System.out.println(min);
    }
    private static void recursion(int a, int b) {
        if (b == M) {
            int tmp = 0;
            for (int i = 0; i < houseCount; i++) {
                int tmpMin = Integer.MAX_VALUE;
                for (int j = 0; j < chickenCount; j++) {
                    if (visited[j]) {
                        tmpMin = Math.min(tmpMin, distances[i][j]);
                    }
                }
                tmp += tmpMin;
            }
            min = Math.min(min, tmp);
            return;
        }
        for (int i = a; i < chickenCount; i++) {
            if (!visited[i]) {
                visited[i] = true;
                recursion(a + 1, b + 1);
                visited[i] = false;
            }
        }
    }
    private static int readInt() throws IOException {
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
}