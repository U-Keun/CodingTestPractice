import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static boolean[] visited;
    private static int[] record;
    private static StringBuilder print;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        record = new int[N];
        visited = new boolean[N];
        print = new StringBuilder();
        backtracking(0);
        System.out.println(print);
    }
    private static void backtracking(int depth) {
        if (depth == N) {
            recordPermutation(record);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            record[depth] = i + 1;
            backtracking(depth + 1);
            visited[i] = false;
        }

    }
    private static void recordPermutation(int[] record) {
        for (int number : record) {
            print.append(number).append(" ");
        }
        print.append("\n");
    }
}