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
        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()),
                v = Integer.parseInt(st.nextToken());

            adjList.get(u - 1).add(v - 1);
            adjList.get(v - 1).add(u - 1);
        }

        boolean[] visited = new boolean[N];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        int[] record = new int[N];
        while (!queue.isEmpty()) {
            int tmp = queue.pollLast();
            visited[tmp] = true;
            for (int v : adjList.get(tmp)) {
                if (visited[v]) continue;
                visited[v] = true;
                record[v] = tmp;
                queue.addLast(v);
            }
        }

        StringBuilder print = new StringBuilder();
        for (int i = 1; i < N; i++) {
            print.append(record[i] + 1);
            if (i != N - 1) print.append("\n");
        }
        System.out.println(print);
    }
}