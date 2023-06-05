import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] scores;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        scores = new int[N];
        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(scores);
        int k = (int) Math.round(N * 15 / 100.0);
        int sum = 0;
        for (int i = k; i < N - k; i++) {
            sum += scores[i];
        }
        System.out.println(Math.round((float) sum / (N - 2 * k)));
    }
}