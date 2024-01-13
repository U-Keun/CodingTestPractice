import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    private static class WeightedVertex {
        int row, col, weight;

        public WeightedVertex(int row, int col, int weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }
    }
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] distances;
    private static int N;
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final String MESSAGE = "Problem %d: %d\n";
    public static void main(String[] args) throws IOException {
        N = readInt();
        int index = 1;
        StringBuilder print = new StringBuilder();
        while (N != 0) {
            prepareFindingPath(N);
            PriorityQueue<WeightedVertex> queue = new PriorityQueue<>(Comparator.comparing(a -> a.weight));
            queue.add(new WeightedVertex(0, 0, map[0][0]));
            distances[0][0] = map[0][0];
            while (!queue.isEmpty()) {
                WeightedVertex tmp = queue.poll();
                if (tmp.row == N - 1 && tmp.col == N - 1) break;
                visited[tmp.row][tmp.col] = true;
                for (int[] direction : directions) {
                    int newRowIndex = tmp.row + direction[0], newColIndex = tmp.col + direction[1];
                    if (newRowIndex >= 0 && newRowIndex < N
                    && newColIndex >= 0 && newColIndex < N
                    && !visited[newRowIndex][newColIndex]) {

                        if (distances[newRowIndex][newColIndex] > distances[tmp.row][tmp.col] + map[newRowIndex][newColIndex]) {
                            distances[newRowIndex][newColIndex] = distances[tmp.row][tmp.col] + map[newRowIndex][newColIndex];
                            queue.add(new WeightedVertex(newRowIndex, newColIndex, distances[newRowIndex][newColIndex]));
                        }
                    }
                }
            }
            print.append(String.format(MESSAGE, index++, distances[N - 1][N - 1]));
            N = readInt();
        }
        System.out.println(print);
    }
    private static void prepareFindingPath(int N) throws IOException {
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = readInt();
            }
        }
        distances = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }
        visited = new boolean[N][N];
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