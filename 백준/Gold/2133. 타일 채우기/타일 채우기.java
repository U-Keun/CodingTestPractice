import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] memo = new int[31];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        memo[0] = 1;
        memo[2] = 3;
        if (N % 2 == 0) {
            for (int i = 4; i <= N; i += 2) {
                memo[i] = 4 * memo[i - 2] - memo[i - 4];
            }
            System.out.println(memo[N]);
        } else System.out.println(0);
    }
}