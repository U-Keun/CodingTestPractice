import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] memo;
    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        setVariables();
        for (int i = 1; i <= N ; i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if (numbers[i] < numbers[j]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                }
            }
        }
        Arrays.sort(memo);
        System.out.println(memo[N]);
    }
    static void setVariables() throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        numbers[0] = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        memo = new int[N + 1];
    }
}