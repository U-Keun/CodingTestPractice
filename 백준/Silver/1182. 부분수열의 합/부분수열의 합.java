import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, S;
    private static int[] numbers;
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(0, 0);
        System.out.println(count);
    }
    private static void backtracking(int index, int value) {
        if (index == N) return;
        if (numbers[index] + value == S) count++;
        backtracking(index + 1, value);
        backtracking(index + 1, value + numbers[index]);
    }
}