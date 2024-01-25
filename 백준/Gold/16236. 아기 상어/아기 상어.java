import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] sea;
    private static final int[][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private static Shark babyShark;

    private static class Shark {
        int row, col, size, eatenFishes;

        Shark(int row, int col) {
            this.row = row;
            this.col = col;
            this.size = 2;
        }

        void eatFish() {
            eatenFishes++;
            if (size == eatenFishes) {
                size++;
                eatenFishes = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sea = new int[N][N];
        babyShark = null;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] == 9) {
                    babyShark = new Shark(i, j);
                    sea[i][j] = 0;
                }
            }
        }

        int totalTime = 0;

        while (true) {
            int time = bfs();
            if (time == -1) {
                break;
            }
            totalTime += time;
        }

        System.out.println(totalTime);
    }

    private static int bfs() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{babyShark.row, babyShark.col});
        visited[babyShark.row][babyShark.col] = true;
        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<int[]> candidates = new ArrayList<>();
            time++;
            for (int i = 0; i < size; i++) {
                int[] position = queue.poll();
                int row = position[0];
                int col = position[1];

                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N
                            && !visited[newRow][newCol] && sea[newRow][newCol] <= babyShark.size) {
                        candidates.add(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }
            candidates.sort((o1, o2) -> {
                if (edible(o1) && !edible(o2))
                    return -1;
                if (!edible(o1) && edible(o2))
                    return 1;
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            });
            if (!candidates.isEmpty() && edible(candidates.get(0))) {
                int r = candidates.get(0)[0], c = candidates.get(0)[1];
                sea[r][c] = 0;
                babyShark.eatFish();
                babyShark.row = r;
                babyShark.col = c;
                return time;
            }
            queue.addAll(candidates);
        }

        return -1;
    }
    private static boolean edible(int[] position) {
        return sea[position[0]][position[1]] > 0 && sea[position[0]][position[1]] < babyShark.size;
    }
}