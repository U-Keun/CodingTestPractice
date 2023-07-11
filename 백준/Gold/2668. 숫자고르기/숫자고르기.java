import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] graph;
    static boolean[] visited;
    static List<Integer> path = new ArrayList<>();
    static Set<Integer> cycleNodes = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            graph[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(i + 1);
            }
        }
        System.out.println(cycleNodes.size());
        for (Integer i:cycleNodes) {
            System.out.println(i);
        }
    }
    static void dfs(int node) {
        visited[node - 1] = true;
        if (node == graph[node - 1]) {
            cycleNodes.add(node);
            return;
        }
        path.add(node);
        if (!visited[graph[node - 1] - 1]) {
            dfs(graph[node - 1]);
        } else {
            int idx = path.indexOf(graph[node - 1]);
            if (idx != -1) {
                for (int i = idx; i < path.size(); i++) {
                    cycleNodes.add(path.get(i));
                }
            }
        }
       path.remove(path.size() - 1);
    }
}