import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, components;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp1 = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());
            graph[tmp1 - 1].add(tmp2);
            graph[tmp2 - 1].add(tmp1);
        }
        components = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            components++;
            dfs(i);
        }
        System.out.println(components);
    }
    static void dfs(int i) {
        if (visited[i]) return;
        visited[i] = true;
        for (int j: graph[i]) {
            dfs(j - 1);
        }
    }
}