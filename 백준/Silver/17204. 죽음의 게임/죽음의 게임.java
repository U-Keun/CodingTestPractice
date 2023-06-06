import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N];
        visited = new boolean[N];
        visited[0] = true;
        for (int i = 0; i < N; i++) {
            graph[i] = Integer.parseInt(br.readLine());
        }
        int k = graph[0];
        int answer = 1;
        while (true) {
            if (visited[k] || k == K) break;
            answer++;
            visited[k] = true;
            k = graph[k];
        }
        if (k == K) System.out.println(answer);
        else System.out.println(-1);
    }
}