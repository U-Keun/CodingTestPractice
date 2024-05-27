import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    private static long[] NA;
    private static StringBuilder print = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        NA = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        br.close();
        print.append(NA[0] - NA[1]).append(" ");
        EEA(NA[0], NA[1], 1, 0, 0, 1);
        System.out.println(print);
    }
    private static void EEA(long r1, long r2, long u1, long u2, long v1, long v2) {
       if (r2 == 0) {
           if (r1 != 1) {
               print.append(-1);
           } else {
               while (v1 < 0) {
                   v1 += NA[0];
               }
               print.append(v1);
           }
           return;
       }
        long q = r1 / r2;
        long r = r1 % r2;
        long u = u1 - q * u2;
        long v = v1 - q * v2;

        EEA(r2, r, u2, u, v2, v);
    }
}