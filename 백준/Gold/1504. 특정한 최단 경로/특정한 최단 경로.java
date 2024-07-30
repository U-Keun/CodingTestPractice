import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        int nextVertex, weight;
        Node link;

        Node(int nextVertex, int weight, Node link) {
            this.nextVertex = nextVertex;
            this.weight = weight;
            this.link = link;
        }
    }
    private static final int INF = 2_000_000_001;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
            Node[] graph = new Node[N + 1];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
                graph[a] = new Node(b, c, graph[a]);
                graph[b] = new Node(a, c, graph[b]);
            }
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()), v2 = Integer.parseInt(st.nextToken());
            int[] fromStart = dijkstra(1, graph), toEnd = dijkstra(N, graph), fromV = dijkstra(v1, graph);
            long path1 = (long) fromStart[v1] + fromV[v2] + toEnd[v2], path2 = (long) fromStart[v2] + fromV[v2] + toEnd[v1];
            if (Math.min(path1, path2) >= INF) bw.write(String.valueOf(-1));
            else bw.write(String.valueOf(Math.min(path1, path2)));
            bw.flush();
        }
    }
    static int[] dijkstra(int start, Node[] graph) {
        int[] weight = new int[graph.length];
        Arrays.fill(weight, INF);
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(array -> array[1]));
        priorityQueue.offer(new int[]{start, 0});
        weight[start] = 0;
        while (!priorityQueue.isEmpty()) {
            int[] tmp = priorityQueue.poll();
            if (weight[tmp[0]] < tmp[1]) continue;
            for (Node node = graph[tmp[0]]; node != null; node = node.link) {
                int value = weight[tmp[0]] + node.weight;
                if (weight[node.nextVertex] > value) {
                    weight[node.nextVertex] = value;
                    priorityQueue.offer(new int[]{node.nextVertex, value});
                }
            }
        }
        return weight;
    }
}