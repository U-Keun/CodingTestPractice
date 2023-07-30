import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, p1 = 0, p2 = 1, answer = 0;
    static ArrayList<Integer> primeNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        primeNumbers.add(0);
        for (int i = 1; i <= N; i++) {
            if (isPrime(i)) {
                primeNumbers.add(primeNumbers.get(primeNumbers.size() - 1) + i);
            }
        }
        while (p1 < p2 && p2 < primeNumbers.size()) {
            if (primeNumbers.get(p2) - primeNumbers.get(p1) < N) {
                p2++;
            } else if (primeNumbers.get(p2) - primeNumbers.get(p1) > N) {
                p1++;
            } else {
                p1++;
                answer++;
            }
        }
        System.out.println(answer);
    }
    static boolean isPrime(int k) {
        if (k == 1) return false;
        boolean answer = true;
        for (int i = 2; i * i <= k; i++) {
            if (k % i == 0) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}