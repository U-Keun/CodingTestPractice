import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder print = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) numbers[i] = i + 1;
        boolean[] visited = new boolean[n];

        combination(numbers, visited, 0, n, m);
        System.out.println(print);

    }

    static void combination(int[] numbers, boolean[] visited, int start, int n, int m) {
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                if (visited[i]) print.append(numbers[i] + " ");
            }
            print.append('\n');
        }

        for (int i = start; i< n; i++) {
            visited[i] = true;
            combination(numbers, visited, i + 1, n, m - 1);
            visited[i] = false;
        }
    }
}