import java.io.*;
import java.util.*;

public class Main {
    private static List<TreeSet<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static int[] order;
    private static int idx = 1;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), R = Integer.parseInt(st.nextToken());
            for (int i = 0; i < N; i++) graph.add(new TreeSet<>());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
                graph.get(u - 1).add(v);
                graph.get(v - 1).add(u);
            }
            visited = new boolean[N];
            order = new int[N];
            dfs(R);
            for (int i = 0; i < N; i++) {
                bw.write(String.valueOf(order[i]));
                bw.write("\n");
            }
            bw.flush();
        }
    }
    private static void dfs(int start) {
        visited[start - 1] = true;
        order[start - 1] = idx++;
        for (Integer v:graph.get(start - 1)) {
            if (!visited[v - 1]) dfs(v);
        }
    }
}