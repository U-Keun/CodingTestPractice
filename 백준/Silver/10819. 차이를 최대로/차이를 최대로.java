import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static boolean[] visited;
    private static int[] input, record;
    private static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        record = new int[N];
        visited = new boolean[N];
        answer = 0;
        backtracking(0);
        System.out.println(answer);
    }
    private static void backtracking(int depth) {
        if (depth == N) {
            answer = Math.max(answer, getStatistic(record));
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            record[depth] = input[i];
            backtracking(depth + 1);
            visited[i] = false;
        }

    }
    private static int getStatistic(int[] record) {
        int l = record.length;
        int result = 0;
        for (int i = 1; i < l; i++) {
            result += Math.abs(record[i] - record[i - 1]);
        }
        return result;
    }
}