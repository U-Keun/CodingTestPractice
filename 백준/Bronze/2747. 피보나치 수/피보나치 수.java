import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static long[] fibonacci = new long[46];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            fibonacci[i] = fibonacci[i - 2] + fibonacci[i - 1];
        }
        System.out.println(fibonacci[n]);
    }
}