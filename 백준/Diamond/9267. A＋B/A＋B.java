import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger[] abS = Arrays.stream(br.readLine().split(" "))
                .map(BigInteger::new)
                .toArray(BigInteger[]::new);
        br.close();
        if (abS[0].equals(abS[2]) || abS[1].equals(abS[2])) {
            System.out.println("YES");
            return;
        }
        if (abS[0].equals(BigInteger.ZERO) && abS[1].equals(BigInteger.ZERO)) {
            if (abS[2].compareTo(BigInteger.ZERO) > 0) {
                System.out.println("NO");
            } else System.out.println("YES");
            return;
        }
        if (canExpressAsLinearCombination(abS[0], abS[1], abS[2])) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean canExpressAsLinearCombination(BigInteger a, BigInteger b, BigInteger S) {
        BigInteger[] result = extendedGCD(a, b);
        BigInteger gcd = result[0], x = result[1], y = result[2];

        if (!S.mod(gcd).equals(BigInteger.ZERO)) {
            return false;
        }

        x = x.multiply(S.divide(gcd));
        y = y.multiply(S.divide(gcd));

        BigInteger k1 = b.divide(gcd);
        BigInteger k2 = a.divide(gcd);

        BigInteger n;
        if (x.compareTo(BigInteger.ZERO) <= 0) {
            n = x.negate().divide(k1).add(BigInteger.ONE);
            x = x.add(n.multiply(k1));
            y = y.subtract(n.multiply(k2));
            while (y.compareTo(BigInteger.ZERO) > 0) {
                if (extendedGCD(x, y)[0].equals(BigInteger.ONE)) return true;
                if (x.compareTo(BigInteger.ZERO) < 0) {
                    return false;
                }
                x = x.add(k1);
                y = y.subtract(k2);
            }
        } else if (y.compareTo(BigInteger.ZERO) <= 0) {
            n = y.negate().divide(k2).add(BigInteger.ONE);
            y = y.add(n.multiply(k2));
            x = x.subtract(n.multiply(k1));
            while (x.compareTo(BigInteger.ZERO) > 0) {
                if (extendedGCD(x, y)[0].equals(BigInteger.ONE)) return true;
                if (y.compareTo(BigInteger.ZERO) < 0) {
                    return false;
                }
                y = y.add(k2);
                x = x.subtract(k1);
            }
        }

        return false;
    }

    private static BigInteger[] extendedGCD(BigInteger a, BigInteger b) {
        BigInteger x0 = BigInteger.ONE, x1 = BigInteger.ZERO, y0 = BigInteger.ZERO, y1 = BigInteger.ONE;
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger q = a.divide(b);
            BigInteger r = a.mod(b);
            BigInteger x = x0.subtract(q.multiply(x1));
            BigInteger y = y0.subtract(q.multiply(y1));
            a = b;
            b = r;
            x0 = x1;
            x1 = x;
            y0 = y1;
            y1 = y;
        }
        return new BigInteger[]{a, x0, y0};
    }
}