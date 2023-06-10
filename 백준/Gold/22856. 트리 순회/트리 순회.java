import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, parent, child1, child2, count;
    static Map<Integer, int[]> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            parent = Integer.parseInt(st.nextToken());
            child1 = Integer.parseInt(st.nextToken());
            child2 = Integer.parseInt(st.nextToken());
            graph.put(parent, new int[]{child1, child2});
        }
        count = 0;
        dfs(1);
        System.out.println(count - rightHeight());
    }
    static void dfs(int vtx) {
        if (visited[vtx - 1]) return;
        visited[vtx - 1] = true;
        for (int i:graph.get(vtx)) {
            if (i == -1) continue;
            count++;
            dfs(i);
            count++;
        }
    }
    static int rightHeight() {
        int answer = 0;
        int start = 1;
        while (graph.get(start)[1] != -1) {
            start = graph.get(start)[1];
            answer++;
        }
        return answer;
    }
}