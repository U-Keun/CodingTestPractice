import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int vertices = Integer.parseInt(br.readLine());
        int connections = Integer.parseInt(br.readLine());
        graph = new int[vertices][vertices];
        visited = new boolean[vertices];
        String[] input;
        int a, b;
        for (int i = 0; i < connections; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]) - 1;
            b = Integer.parseInt(input[1]) - 1;

            if (graph[a][b] == 0) graph[a][b] = 1;
            if (graph[b][a] == 0) graph[b][a] = 1;
        }

        System.out.println(dfs(0) - 1);
    }
    static int dfs(int currentNode) {
        int answer = 0;
        if (visited[currentNode]) {
            return answer;
        }
        visited[currentNode] = true;
        answer++;
        for (int i = 0; i < graph[currentNode].length; i++) {
            if (graph[currentNode][i] == 0) continue;
            answer += dfs(i);
        }
        return answer;
    }
}