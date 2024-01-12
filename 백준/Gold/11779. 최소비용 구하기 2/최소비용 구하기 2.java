import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main {
    private static class WeightedEdge {
        int nextVertex, weight;

        public WeightedEdge(int nextVertex, int weight) {
            this.nextVertex = nextVertex;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        int n = readInt(), m = readInt();
        List<List<WeightedEdge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int s = readInt(), e = readInt(), wt = readInt();
            graph.get(s - 1).add(new WeightedEdge(e, wt));
        }
        int source = readInt(), target = readInt();
        PriorityQueue<WeightedEdge> queue = new PriorityQueue<>(Comparator.comparing(a -> a.weight));
        int[] distances = new int[n + 1], previousVertex = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        queue.add(new WeightedEdge(source, 0));
        distances[source] = 0;
        while (!queue.isEmpty()) {
            WeightedEdge tmp = queue.poll();
            if (tmp.nextVertex == target) break;
            if (distances[tmp.nextVertex] < tmp.weight) continue;
            for (WeightedEdge edge : graph.get(tmp.nextVertex - 1)) {
                if (distances[edge.nextVertex] > tmp.weight + edge.weight) {
                    distances[edge.nextVertex] = tmp.weight + edge.weight;
                    previousVertex[edge.nextVertex] = tmp.nextVertex;
                    queue.add(new WeightedEdge(edge.nextVertex, distances[edge.nextVertex]));
                }
            }
        }
        StringBuilder print = new StringBuilder();
        print.append(String.format("%d\n", distances[target]));
        Stack<Integer>  path = findPath(previousVertex, target);
        print.append(String.format("%d\n", path.size()));
        while (!path.isEmpty()) print.append(String.format("%d ", path.pop()));
        System.out.println(print);
    }
    private static Stack<Integer> findPath(int[] previousVertex, int target) {
        Stack<Integer> result = new Stack<>();
        int prev = target;
        while (previousVertex[prev] != 0) {
            result.push(prev);
            prev = previousVertex[prev];
        }
        result.push(prev);
        return result;
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
}