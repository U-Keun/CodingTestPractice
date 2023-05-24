import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static int n, m;
    static int[] arr;
    static int[] numbers;
    static HashSet<String> snapshots = new HashSet<>();
    static void dfs(int depth) {
        if (depth == m) {
            if (snapshots.contains(Arrays.toString(arr))) return;
            snapshots.add(Arrays.toString(arr));
            for (int i:arr) {
                print.append(i + " ");
            }
            print.append('\n');
            return;
        }
        for (int i = 0; i < n; i++) {
            arr[depth] = numbers[i];
            dfs(depth + 1);

        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        dfs(0);

        System.out.println(print);
    }
}