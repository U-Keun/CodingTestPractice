import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }
        int[] parentList = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int indexToDelete = Integer.parseInt(br.readLine());
        nodes[indexToDelete] = null;
        br.close();
        Node root = null;
        for (int i = 0; i < N; i++) {
            if (parentList[i] == -1) {
                root = nodes[i];
                continue;
            }
            if (nodes[i] != null && nodes[parentList[i]] != null) nodes[parentList[i]].addChild(nodes[i]);
        }
        if (root == null) {
            System.out.println(0);
        } else System.out.println(countLeafNodes(root));
    }
    private static int countLeafNodes(Node node) {
        int answer = 0;
        if (node.children.isEmpty()) {
            return 1;
        }
        for (Node child : node.children) {
            answer += countLeafNodes(child);
        }
        return answer;
    }
    private static class Node {
        int index;
        List<Node> children;
        Node(int index) {
            this.index = index;
            children = new ArrayList<>();
        }
        void addChild(Node child) {
            children.add(child);
        }
    }
}