import java.io.*;
import java.util.*;

public class Main {
    private static class Edge implements Comparable<Edge> {
        int next, weight;

        public Edge(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
    static List<List<Edge>> graph;
    static int N;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken()), X = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken()), v2 = Integer.parseInt(st.nextToken()), wt = Integer.parseInt(st.nextToken());
                graph.get(v1 - 1).add(new Edge(v2, wt));
            }
            int[][] shortestDistance = new int[N][N];
            for (int i = 1; i <= N; i++) {
                shortestDistance[i - 1] = getWeightSum(i);
            }
            int max = 0;
            for (int i = 0; i < N; i++) {
                max = Math.max(max, shortestDistance[i][X - 1] + shortestDistance[X - 1][i]);
            }
            bw.write(String.valueOf(max));
            bw.flush();
        }
    }
    private static int[] getWeightSum(int start) {
        int[] weightSum = new int[N];
        Arrays.fill(weightSum, 1000001);
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        weightSum[start - 1] = 0;
        queue.add(new Edge(start, 0));
        while (!queue.isEmpty()) {
            Edge tmp = queue.poll();
            if (weightSum[tmp.next - 1] < tmp.weight) continue;
            for (Edge edge : graph.get(tmp.next - 1)) {
                if (weightSum[edge.next - 1] > tmp.weight + edge.weight) {
                    weightSum[edge.next - 1] = tmp.weight + edge.weight;
                    queue.add(new Edge(edge.next, weightSum[edge.next - 1]));
                }
            }
        }
        return weightSum;
    }
}