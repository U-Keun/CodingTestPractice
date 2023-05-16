import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] graph;
    static boolean[] visited;
    static String dfsAnswer = "";
    static String bfsAnswer = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int V = Integer.parseInt(input[2]);
        graph = new int[N][N];
        visited = new boolean[N];
        StringTokenizer st;
        int i, e;
        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken()) - 1;
            e = Integer.parseInt(st.nextToken()) - 1;
            if (graph[i][e] == 0) {
                graph[i][e] = 1;
                graph[e][i] = 1;
            }
        }
        dfs(V - 1);
        visited = new boolean[N];
        bfs(V - 1);
        System.out.println(dfsAnswer);
        System.out.println(bfsAnswer);

    }
    static void dfs(int currentNode) {
        if (visited[currentNode]) return;

        visited[currentNode] = true;
        dfsAnswer += (currentNode + 1) + " ";
        for (int i = 0; i < graph[currentNode].length; i++) {
            if (graph[currentNode][i] == 1) dfs(i);
        }
    }
    static void bfs(int currentNode) {
        Queue<Integer> queue = new LinkedList<>();
        visited[currentNode] = true;
        bfsAnswer += (currentNode + 1) + " ";
        queue.add(currentNode);
        int k;
        while (!queue.isEmpty()) {
            k = queue.poll();
            for (int i = 0; i < graph[k].length; i++) {
                if (graph[k][i] == 1) {
                    if (visited[i]) continue;
                    visited[i] = true;
                    bfsAnswer += (i + 1) + " ";
                    queue.add(i);
                }
            }
        }
    }
}