import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static final long INFINITY = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        Graph graph = Graph.getInstance(N);
        for (int i = 0; i < M; i++) {
            graph.addEdge(readInt(), readInt(), readInt());
        }
        BellmanFord bellmanFord = BellmanFord.getInstance(graph, 1);
        long[] distances = bellmanFord.getShortestDistances();
        StringBuilder print = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (distances[i] == INFINITY) {
                print.append("-1");
            } else print.append(distances[i]);
            print.append("\n");
        }
        if (isSourceIsolated(distances)) {
            System.out.println(print);
            return;
        }
        if (bellmanFord.hasNegativeWeightedCycle()) {
            System.out.println(-1);
            return;
        }
        System.out.println(print);
    }
    private static boolean isSourceIsolated(long[] distances) {
        if (distances.length == 1) return false;
        for (int i = 2; i < distances.length; i++) {
            if (distances[i] != INFINITY) return false;
        }
        return true;
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
}

class BellmanFord {
    private Graph graph;
    private long[] distances;
    private static final long INFINITY = Long.MAX_VALUE;

    private BellmanFord(Graph graph, int source) {
        this.graph = graph;
        int n = graph.getVertexCount();
        this.distances = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i != source) distances[i] = INFINITY;
        }
        relaxWeights();
    }

    public static BellmanFord getInstance(Graph graph, int source) {
        return new BellmanFord(graph, source);
    }

    private void relaxWeights() {
        int n = graph.getVertexCount();
        List<Edge> edges = graph.getEdges();
        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                if (distances[edge.getSource()] == INFINITY) continue;
                if (distances[edge.getDestination()] > distances[edge.getSource()] + edge.getWeight()) {
                    distances[edge.getDestination()] = distances[edge.getSource()] + edge.getWeight();
                }
            }
        }
    }

    public long[] getShortestDistances() {
        return this.distances;
    }

    public boolean hasNegativeWeightedCycle() {
        for (Edge edge : graph.getEdges()) {
            if (distances[edge.getDestination()] > distances[edge.getSource()] + edge.getWeight()) {
                return true;
            }
        }
        return false;
    }
}

class Graph {
    private final int vertexCount;
    private List<Edge> edges;

    private Graph(int n) {
        this.vertexCount = n;
        edges = new ArrayList<>();
    }

    public static Graph getInstance(int n) {
        return new Graph(n);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(int source, int destination, long weight) {
        edges.add(Edge.getInstance(source, destination, weight));
    }
}

class Edge {
    private final int source;
    private final int destination;
    private final long weight;

    private Edge(int source, int destination, long weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public static Edge getInstance(int source, int destination, long weight) {
        return new Edge(source, destination, weight);
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public long getWeight() {
        return weight;
    }
}