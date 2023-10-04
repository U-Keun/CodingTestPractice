import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Edge {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
    static List<List<Edge>> graph;
    static boolean[] visited;
    static int diameter = 0, record = 0;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int V = Integer.parseInt(br.readLine());
            StringTokenizer st;
            graph = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < V; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                while (true) {
                    int a = Integer.parseInt(st.nextToken());
                    if (a == -1) break;
                    int b = Integer.parseInt(st.nextToken());
                    graph.get(u - 1).add(new Edge(u, a, b));
                }
            }
            visited = new boolean[V];
            dfs(1, 0);
            dfs(record, 0);
            bw.write(String.valueOf(diameter));
            bw.flush();
        }
    }
    static void dfs(int start, int length) {
        if (visited[start - 1]) return;
        visited[start - 1] = true;
        if (length > diameter) {
            record = start;
            diameter = length;
        }
        for (Edge edge:graph.get(start - 1)) {
            dfs(edge.v, length + edge.weight);
        }
        visited[start - 1] = false;
    }
}