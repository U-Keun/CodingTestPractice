import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, initial, terminal;
    static ArrayList<ArrayList<WeightedEdge>> graph;
    static long[] weightSum;

    public static void main(String[] args) throws IOException {
        setVariables();
        PriorityQueue<WeightedEdge> queue = new PriorityQueue<>(Comparator.comparing(a -> a.weight));
        queue.add(new WeightedEdge(initial, 0));
        weightSum[initial - 1] = 0;
        while (!queue.isEmpty()) {
            WeightedEdge tmp = queue.poll();
            if (weightSum[tmp.nextVertex - 1] < tmp.weight) continue;
            for (WeightedEdge edge:graph.get(tmp.nextVertex - 1)) {
                if (weightSum[edge.nextVertex - 1] > tmp.weight + edge.weight) {
                    weightSum[edge.nextVertex - 1] = tmp.weight + edge.weight;
                    queue.add(new WeightedEdge(edge.nextVertex, weightSum[edge.nextVertex - 1]));
                }
            }
        }
        System.out.println(weightSum[terminal - 1]);
    }
    static void setVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        weightSum = new long[N];
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            weightSum[i] = Long.MAX_VALUE;
        }
        int v1, v2, wt;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            wt = Integer.parseInt(st.nextToken());
            graph.get(v1 - 1).add(new WeightedEdge(v2, wt));
        }
        st = new StringTokenizer(br.readLine());
        initial = Integer.parseInt(st.nextToken());
        terminal = Integer.parseInt(st.nextToken());
    }
}
class WeightedEdge {
    int nextVertex;
    long weight;
    public WeightedEdge(int nextVertex, long weight) {
        this.nextVertex = nextVertex;
        this.weight = weight;
    }
}