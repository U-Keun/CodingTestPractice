import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, count;
    static char[][] campus;
    static boolean[][] visited;
    static Integer[] start;
    static Queue<Integer[]> search;
    static int[] x = {-1, 0, 0, 1}, y = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        campus = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            campus[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (campus[i][j] == 'I') start = new Integer[]{i, j};
            }
        }
        search = new LinkedList<>();
        search.add(start);
        count = 0;
        while (!search.isEmpty()) {
            for (int i = 0; i < search.size(); i++) {
                Integer[] tmp = search.poll();
                if (visited[tmp[0]][tmp[1]]) continue;
                visited[tmp[0]][tmp[1]] = true;
                if (campus[tmp[0]][tmp[1]] == 'P') count++;
                for (int j = 0; j < 4; j++) {
                    if (tmp[0] + y[j] >= 0 && tmp[0] + y[j] < N
                        && tmp[1] + x[j] >= 0 && tmp[1] + x[j] < M
                        && campus[tmp[0] + y[j]][tmp[1] + x[j]] != 'X') {
                        search.add(new Integer[]{tmp[0] + y[j], tmp[1] + x[j]});
                    }
                }
            }
        }
        if (count == 0) System.out.println("TT");
        else System.out.println(count);
    }
}