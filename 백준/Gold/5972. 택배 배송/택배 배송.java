import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static ArrayList<ArrayList<WeightedEdge>> graph;
    static int[] weightSum;

    public static void main(String[] args) throws IOException {
        setVariables();
        PriorityQueue<WeightedEdge> queue = new PriorityQueue<>(Comparator.comparing(a -> a.weight));
        queue.offer(new WeightedEdge(1, 0));
        weightSum[0] = 0;
        while (!queue.isEmpty()) {
            WeightedEdge tmp = queue.poll();
            if (weightSum[tmp.nextVertex - 1] < tmp.weight) continue;
            for (WeightedEdge edge:graph.get(tmp.nextVertex - 1)) {
                if (weightSum[edge.nextVertex - 1] > tmp.weight + edge.weight) {
                    weightSum[edge.nextVertex - 1] = tmp.weight + edge.weight;
                    queue.offer(new WeightedEdge(edge.nextVertex, weightSum[edge.nextVertex - 1]));
                }
            }
        }
        System.out.println(weightSum[N - 1]);
    }
    static void setVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        weightSum = new int[N];
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            weightSum[i] = 50000001;
        }
        int v1, v2, wt;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            wt = Integer.parseInt(st.nextToken());
            graph.get(v1 - 1).add(new WeightedEdge(v2, wt));
            graph.get(v2 - 1).add(new WeightedEdge(v1, wt));
        }
    }
}
class WeightedEdge {
    int nextVertex;
    int weight;
    public WeightedEdge(int nextVertex, int weight) {
        this.nextVertex = nextVertex;
        this.weight = weight;
    }
}