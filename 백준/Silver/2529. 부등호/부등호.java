import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static String[] inequalities;
    private static boolean[] visited;
    private static int[] record;
    private static long max, min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inequalities = br.readLine().split(" ");
        record = new int[N + 1];
        visited = new boolean[10];
        max = 0;
        min = 10000000000L;
        backtracking(0);
        System.out.printf("%0" + (N + 1) + "d%n", max);
        System.out.printf("%0" + (N + 1) + "d", min);
    }
    private static void backtracking(int depth) {
        if (depth == N + 1) {
            long number = getValue(record);
            max = Math.max(max, number);
            min = Math.min(min, number);
            return;
        }
        if (depth == 0) {
            for (int i = 0; i < 10; i++) {
                visited[i]= true;
                record[depth] = i;
                backtracking(depth + 1);
                visited[i] = false;
            }
        } else if (inequalities[depth - 1].equals("<")) {
            for (int i = record[depth - 1] + 1; i < 10; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                record[depth] = i;
                backtracking(depth + 1);
                visited[i] = false;
            }
        } else if (inequalities[depth - 1].equals(">")){
            for (int i = 0; i < record[depth - 1]; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                record[depth] = i;
                backtracking(depth + 1);
                visited[i] = false;
            }
        }
    }
    private static long getValue(int[] record) {
        long answer = 0;
        for (int i = N; i >= 0; i--) {
            answer += record[N - i] * (long) Math.pow(10, i);
        }
        return answer;
    }
}