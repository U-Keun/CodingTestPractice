import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, r, max = Integer.MIN_VALUE, itemCount;
    static int[] weightSum, items;
    static ArrayList<ArrayList<WeightedEdge>> graph;

    public static void main(String[] args) throws IOException {
        setVariable();
        PriorityQueue<WeightedEdge> queue = new PriorityQueue<>(Comparator.comparing(a -> a.weight));
        for (int i = 0; i < n; i++) {
            queue.add(new WeightedEdge(i + 1, 0));
            initWeightSum();
            weightSum[i] = 0;
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
            itemCount = 0;
            for (int j = 0; j < n; j++) {
                if (weightSum[j] <= m) {
                    itemCount += items[j];
                }
            }
            max = Math.max(max, itemCount);
        }
        System.out.println(max);
    }
    static void setVariable() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        weightSum = new int[n];
        items = new int[n];
        st = new StringTokenizer(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int wt = Integer.parseInt(st.nextToken());
            graph.get(v1 - 1).add(new WeightedEdge(v2, wt));
            graph.get(v2 - 1).add(new WeightedEdge(v1, wt));
        }
    }
    static void initWeightSum() {
        for (int i = 0; i < n; i++) {
            weightSum[i] = 3001;
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