import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] memo;
    static List<Integer> hexNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        memo = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            if (i < 6) memo[i] = i;
            else memo[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i * (2 * i - 1) < N + 1; i++) {
            memo[i * (2 * i - 1)] = 1;
            hexNumbers.add(i * (2 * i - 1));
        }
        fillMemo(N);
        System.out.println(memo[N]);
    }
    static void fillMemo(int k) {
        for (Integer i:hexNumbers) {
            for (int j = i + 1; j < k + 1; j++) {
                memo[j] = Math.min(memo[j], 1 + memo[j - i]);
            }
        }
    }
}