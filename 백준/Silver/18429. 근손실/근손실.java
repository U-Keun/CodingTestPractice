import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N, K;
    private static int[] weights;
    private static boolean[] visited;
    private static int currentWeight, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = readIntegers(br.readLine());
        N = input[0];
        K = input[1];
        weights = readIntegers(br.readLine());
        visited = new boolean[N];
        currentWeight = 500;
        count = 0;
        backtracking(0);
        System.out.println(count);
    }
    private static void backtracking(int depth) {
        if (depth == N) {
            count++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i] || currentWeight + weights[i] - K < 500) continue;
            visited[i] = true;
            currentWeight += weights[i] - K;
            backtracking(depth + 1);
            currentWeight -= weights[i] - K;
            visited[i] = false;
        }
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}