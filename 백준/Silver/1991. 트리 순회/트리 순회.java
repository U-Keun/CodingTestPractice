import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder preorder = new StringBuilder();
    static StringBuilder inorder = new StringBuilder();
    static StringBuilder postorder = new StringBuilder();
    static int N;
    static char parent, child1, child2;
    static Map<Character, Character[]> tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        tree = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            parent = st.nextToken().charAt(0);
            child1 = st.nextToken().charAt(0);
            child2 = st.nextToken().charAt(0);
            tree.put(parent, new Character[]{child1, child2});
        }
        visited = new boolean[N];
        preorder('A');
        System.out.println(preorder);
        visited = new boolean[N];
        inorder('A');
        System.out.println(inorder);
        visited = new boolean[N];
        postorder('A');
        System.out.println(postorder);
    }
    static void preorder(char c) {
        if (c == '.' || visited[c - 'A']) {
            return;
        }
        visited[c - 'A'] = true;
        preorder.append(c);
        for (char ch:tree.get(c)) {
            preorder(ch);
        }
    }
    static void inorder(char c) {
        if (c == '.' || visited[c - 'A']) {
            return;
        }
        inorder(tree.get(c)[0]);
        visited[c - 'A'] = true;
        inorder.append(c);
        inorder(tree.get(c)[1]);
    }
    static void postorder(char c) {
        if (c == '.' || visited[c - 'A']) {
            return;
        }
        for (char ch:tree.get(c)) {
            postorder(ch);
        }
        visited[c - 'A'] = true;
        postorder.append(c);
    }
}