import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static int n, m;
    static int[] arr;
    static boolean[] visited;
    static int[] numbers;
    static void dfs(int depth, int start) {
        if (depth == m) {
            for (int i:arr) {
                print.append(i + " ");
            }
            print.append('\n');
            return;
        }
        for (int i = start; i < n; i++) {
            arr[depth] = numbers[i];
            dfs(depth + 1, i + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        arr = new int[m];
        visited = new boolean[n];
        numbers = new int[n];
        String[] inputNumbers = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(inputNumbers[i]);
        }
        Arrays.sort(numbers);
        dfs(0, 0);

        System.out.println(print);
    }
}