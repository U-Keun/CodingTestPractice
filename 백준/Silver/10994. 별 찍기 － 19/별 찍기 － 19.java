import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int n = 4 * N - 3;
        int tmp;
        char starOrBlank;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < n; i++) {
            starOrBlank = '*';
            for (int j: sequence(n, i)) {
                tmp = j;
                for (int k = 0; k < tmp; k++) {
                    print.append(starOrBlank);
                }
                starOrBlank = starOrBlank == '*' ? ' ' : '*';
            }
            print.append('\n');
        }
        System.out.println(print);
    }
    static int[] sequence(int n, int i) {
        int t = Math.min(i, n - i - 1);
        int[] output = new int[2 * t + 1];
        for (int j = 0; j < output.length; j++) {
            if (j == output.length / 2) output[j] = n - 2 * t;
            else output[j] = 1;
        }
        return output;
    }
}