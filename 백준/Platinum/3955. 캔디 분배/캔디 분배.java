import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    private static final StringBuilder print = new StringBuilder();
    private static int[] KC;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            KC = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            EEA(KC[0], KC[1] % KC[0], 1, 0, 0, 1);
        }
        br.close();
        System.out.println(print);
    }
    private static void EEA(int r1, int r2, long u1, long u2, long v1, long v2) {
       if (r2 == 0) {
           if (r1 != 1) {
               print.append("IMPOSSIBLE\n");
           } else {
               while (v1 <= 0 || u1 >= 0) {
                   u1 -= KC[1];
                   v1 += KC[0];
               }
               if (v1 > 1000000000) print.append("IMPOSSIBLE\n");
               else print.append(v1).append("\n");
           }
           return;
       }
        int q = r1 / r2;
        int r = r1 % r2;
        long u = u1 - q * u2;
        long v = v1 - q * v2;

        EEA(r2, r, u2, u, v2, v);
    }
}