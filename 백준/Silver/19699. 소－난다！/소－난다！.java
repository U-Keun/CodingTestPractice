import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class Main {
    private static int N;
    private static boolean[] primeNumberRecord, visited;
    private static int[] weights;
    private static TreeSet<Integer> primeNumbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] firstLine = readIntegers(br.readLine());
        N = firstLine[0];
        int M = firstLine[1];
        weights = readIntegers(br.readLine());
        br.close();
        primeNumberRecord = new boolean[9001];
        sieve();
        visited = new boolean[N];
        primeNumbers = new TreeSet<>();
        backtracking(M, 0);
        StringBuilder print = new StringBuilder();
        if (primeNumbers.isEmpty()) {
            System.out.println(-1);
            return;
        }
        for (Integer prime : primeNumbers) {
            print.append(prime).append(" ");
        }
        System.out.println(print);
    }
    private static void backtracking(int depth, int value) {
        if (depth == 0) {
            if (!primeNumberRecord[value]) {
                primeNumbers.add(value);
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(depth - 1, value + weights[i]);
                visited[i] = false;
            }
        }
    }
    static void sieve() { // Sieve of Eratosthenes
        for (int i = 2; i <= 9000; i++) {
            if (!primeNumberRecord[i]) {
                for (int j = i + i; j <= 9000; j += i) {
                    primeNumberRecord[j] = true;
                }
            }
        }
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}