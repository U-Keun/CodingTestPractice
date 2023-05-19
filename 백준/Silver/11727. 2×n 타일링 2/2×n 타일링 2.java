import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[1000];
        sequence[0] = 1;
        sequence[1] = 3;
        for (int i = 0; i < N; i++) {
            if (i < 2) continue;
            sequence[i] = (sequence[i - 1] + 2 * sequence[i - 2]) % 10007;
        }
        System.out.println(sequence[N - 1]);
    }
}