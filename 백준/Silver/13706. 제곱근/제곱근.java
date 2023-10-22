import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            BigInteger input = new BigInteger(br.readLine());
            bw.write(String.valueOf(binarySearch(input)));
            bw.flush();
        }
    }
    private static BigInteger binarySearch(BigInteger number) {
        BigInteger low = BigInteger.ZERO, high = number.divide(BigInteger.TWO);
        while (low.compareTo(high) < 0) {
            BigInteger mid = (low.add(high)).divide(BigInteger.TWO);
            if (number.compareTo(mid.multiply(mid)) <= 0) high = mid;
            else low = mid.add(BigInteger.ONE);
        }
        return low;
    }
}