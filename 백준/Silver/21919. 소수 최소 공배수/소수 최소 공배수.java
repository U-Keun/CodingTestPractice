import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static boolean[] isNotPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();
        isNotPrime = new boolean[1000001];
        sieve();
        long answer = 1;
        for (int number : numbers) {
            if (isNotPrime[number]) continue;
            isNotPrime[number] = true;
            answer *= number;
        }
        if (answer == 1) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }
    private static void sieve() {
        for (int i = 2; i <= 1000000; i++) {
            if (!isNotPrime[i]) {
                for (int j = i + i; j <= 1000000; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }
}