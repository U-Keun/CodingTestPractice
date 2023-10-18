import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int T = Integer.parseInt(br.readLine());
            StringTokenizer st;
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < T; i++) {
                st = new StringTokenizer(br.readLine());
                final int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
                int[] times = new int[N];
                st = new StringTokenizer(br.readLine());
                List<List<Integer>> graph = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    graph.add(new ArrayList<>());
                    times[j] = Integer.parseInt(st.nextToken());
                }
                int[] indegree = new int[N];
                for (int j = 0; j < K; j++) {
                    st = new StringTokenizer(br.readLine());
                    int u = Integer.parseInt(st.nextToken()) - 1, v = Integer.parseInt(st.nextToken()) - 1;
                    graph.get(u).add(v);
                    indegree[v]++;
                }
                int target = Integer.parseInt(br.readLine());
                Queue<Integer> queue = new LinkedList<>();
                int[] dp = new int[N];
                for (int j = 0; j < N; j++) {
                    if (indegree[j] == 0) queue.offer(j);
                    dp[j] = times[j];
                }
                while (!queue.isEmpty()) {
                    Integer tmp = queue.poll();
                    for (Integer v:graph.get(tmp)) {
                        dp[v] = Math.max(dp[v], dp[tmp] + times[v]);
                        indegree[v]--;
                        if (indegree[v] == 0) queue.offer(v);
                    }
                }
                print.append(dp[target - 1]).append('\n');
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
}