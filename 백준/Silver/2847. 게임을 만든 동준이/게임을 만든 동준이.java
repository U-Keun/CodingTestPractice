import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, count, diff;
    static int[] scores;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        scores = new int[N];
        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }
        count = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (i == N - 1 || scores[i] < scores[i + 1]) continue;
            diff = scores[i] - scores[i + 1] + 1;
            count += diff;
            scores[i] -= diff;
        }
        System.out.println(count);
    }
}