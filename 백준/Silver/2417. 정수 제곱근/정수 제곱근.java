import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            long input = Long.parseLong(br.readLine());
            bw.write(String.valueOf(binarySearch(input)));
            bw.flush();
        }
    }
    private static long binarySearch(long number) {
        BigInteger low = BigInteger.ZERO, high = BigInteger.valueOf(number);
        BigInteger mid = BigInteger.ZERO;
        while (low.compareTo(high) <= 0) {
            mid = (low.add(high)).divide(BigInteger.TWO);
            BigInteger value = mid.multiply(mid);
            if (BigInteger.valueOf(number).compareTo(value) < 0) high = mid.subtract(BigInteger.ONE);
            else if (BigInteger.valueOf(number).compareTo(value) > 0) low = mid.add(BigInteger.ONE);
            else break;
        }
        int i = BigInteger.valueOf(number).compareTo(mid.multiply(mid));
        if (i > 0) mid = mid.add(BigInteger.ONE);
        return mid.longValue();
    }
}