import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int N, k;
    static int[] input, score;
    static int[] numbers = new int[1000001];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        score = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            numbers[input[i]] = i + 1;
        }
        for (int i = 0; i < N; i++) {
            k = input[i];
            for (int j = k + k; j <= 1000000 ; j += k) {
                if (numbers[j] != 0) {
                    score[i]++;
                    score[numbers[j] - 1]--;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            print.append(score[i]).append(" ");
        }
        System.out.println(print);
    }
}