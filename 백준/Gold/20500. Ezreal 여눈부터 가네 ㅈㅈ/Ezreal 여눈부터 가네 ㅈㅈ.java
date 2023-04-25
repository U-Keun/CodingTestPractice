import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BigInteger num = BigInteger.valueOf(2).pow(N-1).subtract(BigInteger.valueOf(-1).pow(N));

        BigInteger three = BigInteger.valueOf(3);
        BigInteger one = BigInteger.valueOf(1);
        BigInteger divider = BigInteger.valueOf(1000000007);
        if (num.mod(three).equals(one)) System.out.println(((num.divide(three)).add(one)).mod(divider));
        else System.out.println(num.divide(three).mod(divider));
    }
}