import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static Long[] sequence = new Long[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < 5; i++) {
            if (i < 3) sequence[i] = 1L;
            else sequence[i] = 2L;
        }
        int input;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            for (int j = 0; j < input; j++) {
                if (j < 5) continue;
                sequence[j] = sequence[j - 1] + sequence[j - 5];
            }
            print.append(sequence[input - 1]).append('\n');
        }
        System.out.println(print);
    }
}