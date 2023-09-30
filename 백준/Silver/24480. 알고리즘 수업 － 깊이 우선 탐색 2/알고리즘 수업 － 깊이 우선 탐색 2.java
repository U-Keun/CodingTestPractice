import java.io.*;
import java.util.*;

public class Main {
    private static ArrayList<Integer>[] edges;
    private static boolean[] visited;
    private static int[] order;
    private static int idx = 1;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), R = Integer.parseInt(st.nextToken());
            edges = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                edges[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
                edges[u - 1].add(v);
                edges[v - 1].add(u);
            }
            for (int i = 0; i < N; i++) {
                Collections.sort(edges[i], Comparator.reverseOrder());
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
        for (Integer v: edges[start - 1]) {
            if (!visited[v - 1]) dfs(v);
        }
    }
}