import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static int N, k, rootN;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        k = 2;
        rootN = (int) Math.sqrt(N);
        while (k <= rootN || N != 1) {
            if (N % k == 0) {
                print.append(k).append('\n');
                N /= k;
            } else k++;
        }
        System.out.println(print);
    }
}