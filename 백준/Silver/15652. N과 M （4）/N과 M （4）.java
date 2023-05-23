import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder print = new StringBuilder();
    static int[] numbers;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        for (int i = 0; i < n; i++) numbers[i] = i + 1;
        visited = new int[n];
        combination(n, m);
        System.out.println(print);
    }
    static void combination(int n, int m) {
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                if (visited[i] != 0) {
                    for (int j = 0; j < visited[i]; j++) print.append(numbers[i] + " ");
                }
            }
            print.append('\n');
        } else {
            int start = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (visited[j] != 0) {
                    start = j;
                    break;
                }
            }
            for (int i = start; i < n; i++) {
                visited[i]++;
                combination(n, m - 1);
                visited[i]--;
            }
        }
    }
}