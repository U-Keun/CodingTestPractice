import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    private static final String EDGE = " ";
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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] directory;
        int depth = 0;
        Node root = new Node(0);
        for (int i = 0; i < N; i++) {
            directory = br.readLine().split("\\\\");
            depth = directory.length;
            Node current = root;
            for (int j = 0; j < depth; j++) {
                current.insert(directory[j]);
                current = current.getChildNode(directory[j]);
            }
        }
        System.out.println(root);
    }
}