import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        br.close();

        int l = (int) Math.ceil(Math.log(input[0]) / Math.log(input[2])) + 1;

        long[] padicN = convertToPadicNumber(input[0], input[2], l),
                padicK = convertToPadicNumber(input[1], input[2], l);

        long answer = 1;
        for (int i = 0; i < l; i++) {
            answer *= binomial(padicN[i], padicK[i], input[2]);
            answer %= input[2];
        }

        System.out.println(answer);
    }

    private static long[] convertToPadicNumber(long N, long p, int l) {
        long[] result = new long[l];
        int index = 0;
        while (N > 0) {
            result[index++] = N % p;
            N /= p;
        }
        return result;
    }

    private static long modPower(long a, long n, long mod) {
        long answer = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                answer = (answer * a) % mod;
            }
            a = (a * a) % mod;
            n >>= 1;
        }
        return answer;
    }
    private static long binomial(long n, long k, long mod) {
        if (k > n || n < 0) return 0;
        long num = 1, denom = 1;
        for (int i = 0; i < k; i++) {
            num = (num * (n - i)) % mod;
            denom = (denom * (i + 1)) % mod;
        }
        return (num * modPower(denom, mod - 2, mod)) % mod;
    }
}