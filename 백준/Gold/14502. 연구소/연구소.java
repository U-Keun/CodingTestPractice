import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    private static int N, M;
    private static final List<int[]> virus = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mapSize = br.readLine().split(" ");
        N = Integer.parseInt(mapSize[0]);
        M = Integer.parseInt(mapSize[1]);
        int[][] map = new int[N][M];
        List<int[]> emptySpaces = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    emptySpaces.add(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }
        br.close();
        int l = emptySpaces.size();
        int answer = 0;
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                for (int k = j + 1; k < l; k++) { // 41664 미만
                    int[] wall1 = emptySpaces.get(i),
                            wall2 = emptySpaces.get(j),
                            wall3 = emptySpaces.get(k);
                    map[wall1[0]][wall1[1]] = 1;
                    map[wall2[0]][wall2[1]] = 1;
                    map[wall3[0]][wall3[1]] = 1;
                    answer = Math.max(answer, simulate(map, l - 3));
                    map[wall1[0]][wall1[1]] = 0;
                    map[wall2[0]][wall2[1]] = 0;
                    map[wall3[0]][wall3[1]] = 0;
                }
            }
        }
        System.out.println(answer);
    }
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int simulate(int[][] map, int empty) {
        Queue<int[]> queue = new LinkedList<>(virus); // 최대 64개 들어감
        boolean[][] visited = new boolean[N][M];
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            if (map[tmp[0]][tmp[1]] == 0) empty--;
            for (int i = 0; i < 4; i++) {
                int row = tmp[0] + directions[i][0], col = tmp[1] + directions[i][1];
                if (row >= 0 && row < N &&
                        col >= 0 && col < M &&
                        !visited[row][col] &&
                        map[row][col] == 0) {
                    visited[row][col] = true;
                    queue.add(new int[]{row, col});
                }
            }
        }
        return empty;
    }
}