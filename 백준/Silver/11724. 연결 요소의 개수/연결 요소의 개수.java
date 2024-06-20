import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()),
            M = Integer.parseInt(st.nextToken());
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()),
                v = Integer.parseInt(st.nextToken());

            adjList.get(u - 1).add(v - 1);
            adjList.get(v - 1).add(u - 1);
        }

        boolean[] visited = new boolean[N];
        Deque<Integer> queue = new ArrayDeque<>();

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            answer++;
            visited[i] = true;
            queue.addLast(i);
            while (!queue.isEmpty()) {
                int tmp = queue.pollLast();
                visited[tmp] = true;
                for (int v : adjList.get(tmp)) {
                    if (visited[v]) continue;
                    visited[v] = true;
                    queue.addLast(v);
                }
            }
        }

        System.out.println(answer);
    }
}