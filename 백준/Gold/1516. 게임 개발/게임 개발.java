import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] constructionTime = new int[N];
        int[] inDegree = new int[N];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            constructionTime[i] = Integer.parseInt(st.nextToken());
            String preceding;
            while (!(preceding = st.nextToken()).equals("-1")) {
                int preBuilding = Integer.parseInt(preceding);
                graph.get(preBuilding - 1).add(i);
                inDegree[i]++;
            }
        }
        br.close();
        int[] dp = new int[N];
        Queue<Integer> order = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                order.add(i);
            }
        }
        while (!order.isEmpty()) {
            int tmp = order.poll();
            for (int node : graph.get(tmp)) {
                dp[node] = Math.max(dp[node], dp[tmp] + constructionTime[tmp]);
                inDegree[node]--;
                if (inDegree[node] == 0) {
                    order.add(node);
                }
            }
        }
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N; i++) {
            print.append(dp[i] + constructionTime[i]).append("\n");
        }
        System.out.println(print);
    }
}