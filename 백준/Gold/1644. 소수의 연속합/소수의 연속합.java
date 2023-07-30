import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, p1 = 0, p2 = 0, sum = 0, answer = 0;
    static ArrayList<Integer> primeNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        sieve(N);
        while (p2 <= primeNumbers.size()) {
            if (p2 == primeNumbers.size()) {
                if (p2 != 0 && (sum - primeNumbers.get(p1) == N || sum == N)) answer++;
                break;
            }
            if (sum < N) sum += primeNumbers.get(p2++);
            else if (sum > N) sum -= primeNumbers.get(p1++);
            else {
                sum -= primeNumbers.get(p1++);
                answer++;
            }
        }
        System.out.println(answer);
    }
    static void sieve(int n) { // Sieve of Eratosthenes
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                primeNumbers.add(i);
                for (int j = i + i; j <= n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
    }
}