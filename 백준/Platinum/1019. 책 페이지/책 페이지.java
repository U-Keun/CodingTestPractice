import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long number = Long.parseLong(br.readLine());
        br.close();

        long mod = 1;
        long[] answer = new long[10];
        while (number / mod > 0) {
            long count = number / (mod * 10);

            if (count > 0) {
                answer[0] += (count - 1) * mod;

                for (int i = 1; i < 10; i++) {
                    answer[i] += count * mod;
                }
            }

            long r = number % (mod * 10);

            int bound = (int) (r / mod);

            if (count > 0) {
                if (bound == 0) answer[0] += r % mod + 1;
                else answer[0] += mod;
            }

            for (int i = 1; i < bound; i++) {
                answer[i] += mod;
            }

            if (bound > 0) {
                answer[bound] += r % mod + 1;
            }

            mod *= 10;
        }

        StringBuilder print = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            print.append(answer[i]);
            if (i < 9) print.append(" ");
        }

        System.out.println(print);
    }
}