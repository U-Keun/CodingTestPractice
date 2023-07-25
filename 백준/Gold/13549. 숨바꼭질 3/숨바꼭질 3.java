import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static long[] weightSum;

    public static void main(String[] args) throws IOException {
        setVariable();
        PriorityQueue<WeightedEdge> queue = new PriorityQueue<>(Comparator.comparing(a -> a.weight));
        queue.add(new WeightedEdge(N, 0));
        weightSum[N] = 0;
        while (!queue.isEmpty()) {
            WeightedEdge tmp = queue.poll();
            if (weightSum[tmp.nextVertex] < tmp.weight) continue;
            if (tmp.nextVertex <= 50000) {
                if (weightSum[2 * tmp.nextVertex] > tmp.weight) {
                    weightSum[2 * tmp.nextVertex] = tmp.weight;
                    queue.add(new WeightedEdge(2 * tmp.nextVertex, tmp.weight));
                }
            }
            if (tmp.nextVertex > 0) {
                if (weightSum[tmp.nextVertex - 1] > tmp.weight + 1) {
                    weightSum[tmp.nextVertex - 1] = tmp.weight + 1;
                    queue.add(new WeightedEdge(tmp.nextVertex - 1, weightSum[tmp.nextVertex - 1]));
                }
            }
            if (tmp.nextVertex < 100000) {
                if (weightSum[tmp.nextVertex + 1] > tmp.weight + 1) {
                    weightSum[tmp.nextVertex + 1] = tmp.weight + 1;
                    queue.add(new WeightedEdge(tmp.nextVertex + 1, weightSum[tmp.nextVertex + 1]));
                }
            }
        }
        System.out.println(weightSum[K]);
    }
    static void setVariable() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        weightSum = new long[100001];
        for (int i = 0; i < 100001; i++) {
            weightSum[i] = 100001;
        }
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