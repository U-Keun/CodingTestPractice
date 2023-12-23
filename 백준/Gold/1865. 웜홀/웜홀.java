import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static class Edge {
        private final int source;
        private final int destination;
        private final long weight;

        public Edge(int source, int destination, long weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        int TC = readInt();
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < TC; i++) {
            int N = readInt(), M = readInt(), W = readInt();
            List<Edge> edges = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                int s = readInt(), e = readInt(), t = readInt();
                edges.add(new Edge(s, e, t));
                if (s != e) {
                    edges.add(new Edge(e, s, t));
                }
            }
            for (int j = 0; j < W; j++) {
                int s = readInt(), e = readInt(), t = readInt();
                edges.add(new Edge(s, e, -t));
            }
            long[] distances = new long[N + 1];
            boolean timeTravel = false;
            Loop : for (int j = 0; j <= N; j++) {
                for (Edge edge : edges) {
                    if (distances[edge.destination] > distances[edge.source] + edge.weight) {
                        if (j == N) {
                            timeTravel = true;
                            break Loop;
                        }
                        distances[edge.destination] = distances[edge.source] + edge.weight;
                    }
                }
            }
            if (timeTravel) {
                print.append("YES\n");
            } else print.append("NO\n");
        }
        System.out.println(print);
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