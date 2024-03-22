import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[][] record;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[M + 1];
        for (int i = 1; i <= M; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int edges = Integer.parseInt(st.nextToken());
            int node;
            for (int j = 0; j < edges; j++) {
                node = Integer.parseInt(st.nextToken());
                graph[node].add(i);
            }
        }
        br.close();
        record = new int[N + 1][2];
        int answer = 0;
        for (int i = 1; i <= M; i++) {
            visited = new boolean[N + 1];
            if (findMatching(i)) answer++;
        }
        System.out.println(answer);
    }
    private static boolean findMatching(int task) {
        List<Integer> workers = graph[task];
        for (Integer worker : workers) {
            if (visited[worker]) continue;
            visited[worker] = true;
            if (record[worker][0] == 0 || findMatching(record[worker][0])) {
                record[worker][0] = task;
                return true;
            }
            if (record[worker][1] == 0 || findMatching(record[worker][1])) {
                record[worker][1] = task;
                return true;
            }
        }
        return false;
    }
}