import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] record;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int edges = Integer.parseInt(st.nextToken());
            int node;
            for (int j = 0; j < edges; j++) {
                node = Integer.parseInt(st.nextToken());
                graph[i].add(node);
            }
        }
        br.close();
        record = new int[M + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[M + 1];
            if (findMatching(i)) answer++;
        }
        System.out.println(answer);
    }
    private static boolean findMatching(int cow) {
        List<Integer> barns = graph[cow];
        for (Integer barn : barns) {
            if (visited[barn]) continue;
            visited[barn] = true;
            if (record[barn] == 0 || findMatching(record[barn])) {
                record[barn] = cow;
                return true;
            }
        }
        return false;
    }
}