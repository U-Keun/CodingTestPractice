import java.io.*;

public class Main {
    private static class Edge {
        int v, weight;
        Edge next;

        public Edge(int v, int weight, Edge next) {
            this.v = v;
            this.weight = weight;
            this.next = next;
        }
    }
    private static Edge[] graph;
    private static boolean[] visited;
    private static int diameter = 0, record = 0;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(br.readLine());
            String input;
            graph = new Edge[n + 1];
            while ((input = br.readLine()) != null) {
                String[] split = input.split(" ");
                int a = Integer.parseInt(split[0]),
                    b = Integer.parseInt(split[1]),
                    c = Integer.parseInt(split[2]);
                graph[a] = new Edge(b, c, graph[a]);
                graph[b] = new Edge(a, c, graph[b]);
            }
            visited = new boolean[n + 1];
            dfs(1, 0);
            dfs(record, 0);
            bw.write(String.valueOf(diameter));
            bw.flush();
        }
    }
    private static void dfs(int start, int length) {
        if (visited[start]) return;
        visited[start] = true;
        if (length > diameter) {
            record = start;
            diameter = length;
        }
        for (Edge e = graph[start]; e != null; e = e.next) {
            dfs(e.v, length + e.weight);
        }
        visited[start] = false;
    }
}