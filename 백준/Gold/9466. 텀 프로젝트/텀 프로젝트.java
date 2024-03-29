import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static boolean[] visited, record;
    private static int[] preferences;
    private  static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            preferences = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            visited = new boolean[n];
            record = new boolean[n];
            count = n;
            for (int j = 1; j <= n; j++) {
                if (record[j - 1]) continue;
                dfs(j);
            }
            print.append(count).append("\n");
        }
        br.close();
        System.out.println(print);
    }
    private static void dfs(int current) {
        if (record[current - 1]) return;
        if (visited[current - 1]) {
            record[current - 1] = true;
            count--;
        }
        visited[current - 1] = true;
        dfs(preferences[current - 1]);
        record[current - 1] = true;
        visited[current - 1] = false;
    }
}