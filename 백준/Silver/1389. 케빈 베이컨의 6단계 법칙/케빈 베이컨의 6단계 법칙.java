import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, minKB = Integer.MAX_VALUE, answer = 0, k;
    static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        for (int i = 1; i <= N; i++) {
            k = getKB(i);
            if (k < minKB) {
                minKB = k;
                answer = i;
            }
        }
        System.out.println(answer);
    }
    static int getKB(int i) {
        boolean[] visited = new boolean[N];
        Queue<Integer> vertices = new LinkedList<>();
        vertices.add(i);
        visited[i - 1] = true;
        int t = 1, answer = 0, l, m;
        while (!vertices.isEmpty()) {
            l = vertices.size();
            for (int j = 0; j < l; j++) {
                m = vertices.poll();
                for (int o:graph.get(m)) {
                    if (visited[o - 1]) continue;
                    visited[o - 1] = true;
                    answer += t;
                    vertices.add(o);
                }
            }
            t++;
        }
        return answer;
    }
}