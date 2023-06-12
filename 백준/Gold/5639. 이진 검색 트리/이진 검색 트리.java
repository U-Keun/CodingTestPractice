import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static int k, tmp, root;
    static String input;
    static Map<Integer, Integer[]> graph;
    static Stack<Integer> nodes;
    static Map<Integer, Boolean> visited;

    public static void main(String[] args) throws IOException {
        graph = new HashMap<>();
        nodes = new Stack<>();
        visited = new HashMap<>();
        root = Integer.parseInt(br.readLine());
        visited.put(root, false);
        nodes.push(root);
        graph.put(root, new Integer[]{0, 0});
        while ((input = br.readLine()) != null) {
            k = Integer.parseInt(input);
            visited.put(k, false);
            if (nodes.isEmpty() || nodes.peek() > k) {
                if (!nodes.isEmpty()) {
                    graph.get(nodes.peek())[0] = k;
                }
            } else {
                tmp = nodes.pop();
                while (!nodes.isEmpty() && nodes.peek() < k) {
                    tmp = nodes.pop();
                }
                graph.get(tmp)[1] = k;
            }
            nodes.push(k);
            graph.put(k, new Integer[]{0, 0});
        }
        postorder(root);
        System.out.println(print);
    }
    static void postorder(int c) {
        if (c == 0 || visited.get(c)) {
            return;
        }
        for (int i:graph.get(c)) {
            postorder(i);
        }
        visited.put(c, true);
        print.append(c).append('\n');
    }
}