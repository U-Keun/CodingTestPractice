import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, count;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Integer[]> vertices = new LinkedList<>();
    static int[] x = {-1, 0, 0, 1}, y = {0, 1, -1, 0};
    static List<Integer> answer;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (input[j] == '1') {
                    map[i][j] = 1;
                    vertices.add(new Integer[]{i, j});
                }
            }
        }
        while (!vertices.isEmpty()) {
            count = 0;
            Integer[] vertex = vertices.poll();
            if (visited[vertex[0]][vertex[1]]) continue;
            findAPT(vertex);
            answer.add(count);
        }
        System.out.println(answer.size());
        Collections.sort(answer);
        for (Integer k:answer) {
            System.out.println(k);
        }
    }
    static void findAPT(Integer[] site) {
        if (visited[site[0]][site[1]]) {
            return;
        }
        count++;
        visited[site[0]][site[1]] = true;
        for (int i = 0; i < 4; i++) {
            int newX = site[1] + x[i];
            int newY = site[0] + y[i];
            if (newY >= 0 && newY < N
                && newX >= 0 && newX < N
                && map[newY][newX] == 1) {
                findAPT(new Integer[]{newY, newX});
            }
        }
    }
}