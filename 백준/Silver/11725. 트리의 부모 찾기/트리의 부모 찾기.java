import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static int N, v1, v2, parent;
    static String input;
    static String[] splitInput;
    static Map<Integer, List<Integer>> graph;
    static Queue<Integer> vertices;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        vertices = new LinkedList<>();
        parents = new int[N - 1];
        while ((input = br.readLine()) != null) {
            splitInput = input.split(" ");
            v1 = Integer.parseInt(splitInput[0]);
            v2 = Integer.parseInt(splitInput[1]);
            putEdge(v1, v2);
            putEdge(v2, v1);
        }
        vertices.add(1);
        while (!vertices.isEmpty()) {
            parent = vertices.poll();
            for (int i:graph.getOrDefault(parent, new ArrayList<>(){})) {
                if (i == 1 || parents[i - 2] != 0) continue;
                parents[i - 2] = parent;
                vertices.add(i);
            }
        }
        for (int i:parents) {
            print.append(i).append('\n');
        }
        System.out.println(print);
    }
    static void putEdge(int v1, int v2) {
        if (!graph.containsKey(v1)) {
            graph.put(v1, new ArrayList<>());
        }
        graph.get(v1).add(v2);
    }
}