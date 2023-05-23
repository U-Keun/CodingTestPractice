import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static int n, m;
    static int[] arr;
    static void dfs(int depth) {
        if (depth == m) {
            for (int i:arr) {
                print.append(i + " ");
            }
            print.append('\n');
            return;
        }
        for (int i = 1; i <= n; i++) {
            arr[depth] = i;
            dfs(depth + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        arr = new int[m];
        dfs(0);

        System.out.println(print);
    }
}