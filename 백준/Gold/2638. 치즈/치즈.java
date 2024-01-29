import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] cheeseGrid;
    private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheeseGrid = new int[N][M];
        int totalCheese = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheeseGrid[i][j] = Integer.parseInt(st.nextToken());
                if (cheeseGrid[i][j] == 1) totalCheese++;
            }
        }
        br.close();
        int T = 0;
        while (totalCheese > 0) {
            T++;
            List<int[]> contactedCheeses = searchContactedCheeses();
            for (int[] cheese : contactedCheeses) {
                cheeseGrid[cheese[0]][cheese[1]] = 0;
                totalCheese--;
            }
        }
        System.out.println(T);
    }
    private static List<int[]> searchContactedCheeses() {
        int[][] visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] direction : directions) {
                int newRowIndex = current[0] + direction[0], newColIndex = current[1] + direction[1];
                if (isInGrid(newRowIndex, newColIndex)) {
                    if (cheeseGrid[newRowIndex][newColIndex] == 0) {
                        if (visited[newRowIndex][newColIndex] == 0) continue;
                        visited[newRowIndex][newColIndex] = 0;
                        queue.add(new int[]{newRowIndex, newColIndex});
                        continue;
                    }
                    visited[newRowIndex][newColIndex] = Math.max(visited[newRowIndex][newColIndex], 0) + 1;
                }
            }
        }
        return getPositions(visited);
    }
    private static boolean isInGrid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
    private static List<int[]> getPositions(int[][] visited) {
        List<int[]> positions = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] >= 2) {
                    positions.add(new int[]{i, j});
                }
            }
        }
        return positions;
    }
}