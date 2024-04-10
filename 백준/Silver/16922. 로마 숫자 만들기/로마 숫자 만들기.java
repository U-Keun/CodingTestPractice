import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean[] visited;
    private static int answer;
    private static final int[] signs = {1, 5, 10, 50};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[1001];
        answer = 0;
        backtracking(N, 0, 0);
        System.out.println(answer);
    }
    private static void backtracking(int depth, int start, int sum) {
        if (depth == 0) {
            if (!visited[sum]) {
                visited[sum] = true;
                answer++;
            }
            return;
        }
        for (int i = start; i < signs.length; i++) {
            backtracking(depth - 1, i, sum + signs[i]);
        }
    }
}