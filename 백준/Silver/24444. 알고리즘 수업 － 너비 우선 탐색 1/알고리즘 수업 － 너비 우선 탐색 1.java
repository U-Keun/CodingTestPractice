import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), R = Integer.parseInt(st.nextToken());
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
                graph.get(u - 1).add(v);
                graph.get(v - 1).add(u);
            }
            for (int i = 0; i < N; i++) {
                Collections.sort(graph.get(i));
            }
            int[] visited = new int[N + 1];
            Arrays.fill(visited, -1);
            Queue<Integer> queue = new LinkedList<>();
            visited[R] = 1;
            int order = 2;
            queue.add(R);
            while (!queue.isEmpty()) {
                int tmp = queue.poll();
                for (int i:graph.get(tmp - 1)) {
                    if (visited[i] == -1) {
                        visited[i] = order++;
                        queue.add(i);
                    }
                }
            }
            StringBuilder print = new StringBuilder();
            for (int i = 1; i <= N; i++) {
                if (visited[i] < 0) print.append(0);
                else print.append(visited[i]);
                print.append('\n');
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
}