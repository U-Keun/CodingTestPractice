import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private static int N, M;
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        int[][] map = new int[M][N];
        int[][] distances = new int[M][N];
        boolean[][] visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = row[j] - '0';
                distances[i][j] = 10001;
            }
        }
        br.close();
        dijkstra(map, distances, visited);
        System.out.println(distances[M - 1][N - 1]);
    }
    private static void dijkstra(int[][] map, int[][] distances, boolean[][] visited) {
        PriorityQueue<WeightedVertex> queue = new PriorityQueue<>(Comparator.comparing(a -> a.weight));
        queue.add(new WeightedVertex(0, 0, map[0][0]));
        distances[0][0] = map[0][0];
        while (!queue.isEmpty()) {
            WeightedVertex tmp = queue.poll();
            if (tmp.row == N - 1 && tmp.col == M - 1) break;

            visited[tmp.row][tmp.col] = true;
            if (distances[tmp.row][tmp.col] < tmp.weight) continue;

            for (int[] direction : directions) {
                int newRowIndex = tmp.row + direction[0], newColIndex = tmp.col + direction[1];
                if (outOfBound(newRowIndex, newColIndex) || visited[newRowIndex][newColIndex]) continue;

                if (distances[newRowIndex][newColIndex] > distances[tmp.row][tmp.col] + map[newRowIndex][newColIndex]) {
                    distances[newRowIndex][newColIndex] = distances[tmp.row][tmp.col] + map[newRowIndex][newColIndex];
                    queue.add(new WeightedVertex(newRowIndex, newColIndex, distances[newRowIndex][newColIndex]));
                }
            }
        }
    }
    private static boolean outOfBound(int row, int col) {
        return row < 0 || col < 0 || row >= M || col >= N;
    }
}