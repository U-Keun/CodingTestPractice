import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static BigInteger[] fibonacci = new BigInteger[10001];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        fibonacci[0] = BigInteger.ZERO;
        fibonacci[1] = BigInteger.ONE;
        for (int i = 2; i < n + 1; i++) {
            fibonacci[i] = fibonacci[i - 2].add(fibonacci[i - 1]);
        }
        System.out.println(fibonacci[n]);
    }
}