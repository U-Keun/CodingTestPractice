import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean[] isNotPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isNotPrime = new boolean[1100001];
        isNotPrime[1] = true;
        sieve();
        int n = Integer.parseInt(br.readLine());
        br.close();
        for (int i = n; i <= 1100000; i++) {
            if (!isNotPrime[i] && isPalindrome(i)) {
                System.out.println(i);
                return;
            }
        }
    }
    private static void sieve() {
        for (int i = 2; i <= 1000; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= 1100000; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }
    private static boolean isPalindrome(int k) {
        String original = Integer.toString(k);
        int n = original.length();
        for (int i = 0; i < n / 2; i++) {
            if (original.charAt(i) != original.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}