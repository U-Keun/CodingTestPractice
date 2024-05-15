import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean[] isNotPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isNotPrime = new boolean[100001];
        isNotPrime[1] = true;
        sieve();
        int T = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int index = 2;
            while (n > 1) {
                if (isNotPrime[index]) {
                    index++;
                    continue;
                }
                int count = 0;
                while (n % index == 0) {
                    count++;
                    n /= index;
                }
                if (count == 0) {
                    index++;
                    continue;
                }
                print.append(index)
                        .append(" ")
                        .append(count)
                        .append("\n");
                index++;
            }
        }
        br.close();
        System.out.println(print);
    }

    private static void sieve() {
        for (int i = 2; i <= Math.sqrt(100000); i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= 100000; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }
}