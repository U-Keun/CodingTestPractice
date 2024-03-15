import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    private static final String EDGE = "--";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input;
        int depth;
        Node root = new Node(0);
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            depth = Integer.parseInt(input[0]);
            Node current = root;
            for (int j = 0; j < depth; j++) {
                current.insert(input[j + 1]);
                current = current.getChildNode(input[j + 1]);
            }
        }
        System.out.println(root);
    }

    private static class Node {
        Map<String, Node> children;
        int depth;
        Node(int depth) {
            children = new TreeMap<>();
            this.depth = depth;
        }
        void insert(String word) {
            children.putIfAbsent(word, new Node(depth + 1));
        }
        Node getChildNode(String word) {
            return children.get(word);
        }
        @Override
        public String toString() {
            StringBuilder print = new StringBuilder();
            String edge = EDGE.repeat(depth);
            for (String key : children.keySet()) {
                print.append(edge).append(key).append("\n");
                print.append(children.get(key));
            }
            return print.toString();
        }
    }
}