import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        long[] inverses = getInverses();

        while (T-- > 0) {
            long[] NM = Arrays.stream(br.readLine().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();
            if (NM[0] == 0) {
                if (NM[1] == 1) print.append(1);
                else print.append(0);
                print.append("\n");
                continue;
            } else if (NM[1] == 1) {
                print.append(0).append("\n");
                continue;
            }

            double logK = Math.log(Math.max(NM[0], NM[1]));

            int n1 = (int) Math.ceil(logK / Math.log(97)) + 1,
                n2 = (int) Math.ceil(logK / Math.log(1031)) + 1;

            long[] padicN1 = convertToPadicNumber(NM[0] - 1, 97, n1),
                    padicN2 = convertToPadicNumber(NM[0] - 1, 1031, n2),
                    padicM1 = convertToPadicNumber(NM[1] - 2, 97, n1),
                    padicM2 = convertToPadicNumber(NM[1] - 2, 1031, n2);

            long root1 = 1;
            for (int i = 0; i < n1; i++) {
                root1 *= binomial(padicN1[i], padicM1[i], 97);
                root1 %= 97;
            }
            root1 *= inverses[0];
            root1 %= 97;

            long root2 = 1;
            for (int i = 0; i < n2; i++) {
                root2 *= binomial(padicN2[i], padicM2[i], 1031);
                root2 %= 1031;
            }
            root2 *= inverses[1];
            root2 %= 1031;
            
            print.append((1031 * root1 + 97 * root2) % 100007).append("\n");
        }
        br.close();
        System.out.println(print);
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
    private static long[] getInverses() {
        long a = 1031, b = 97;
        long x0 = 1, x1 = 0, y0 = 0, y1 = 1;
        long q, r, x, y;
        while (b != 0) {
            q = a / b;
            r = a % b;
            x = x0 - q * x1;
            y = y0 - q * y1;
            a = b;
            b = r;
            x0 = x1;
            x1 = x;
            y0 = y1;
            y1 = y;
        }

        y0 += 1031;
        return new long[]{x0, y0};
    }
}