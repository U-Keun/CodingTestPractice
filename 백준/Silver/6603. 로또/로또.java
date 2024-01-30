import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int k;
    private static int[] numbers;
    private static StringBuilder print = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        while ((k = Integer.parseInt(st.nextToken())) != 0) {
            numbers = new int[k];
            for (int i = 0; i < k; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            backtracking(0, new ArrayList<>());
            print.append("\n");
            st = new StringTokenizer(br.readLine());
        }
        System.out.println(print);
    }
    private static void backtracking(int index, List<Integer> candidate) {
        if (candidate.size() == 6) {
            candidate.forEach(n -> print.append(n).append(" "));
            print.append("\n");
            return;
        }
        if (index == k) return;
        List<Integer> updated = new ArrayList<>(candidate);
        updated.add(numbers[index]);
        backtracking(index + 1, updated);
        backtracking(index + 1, candidate);
    }
}