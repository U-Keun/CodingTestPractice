import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger[] input = readIntegers(br.readLine());
        br.close();
        System.out.println(input[0].divide(input[1]));
        System.out.println(input[0].mod(input[1]));
    }
    private static BigInteger[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .map(BigInteger::new)
                .toArray(BigInteger[]::new);
    }
}