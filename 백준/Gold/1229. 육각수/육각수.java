import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] memo = new int[1000001];
    static List<Integer> hexNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i * (2 * i - 1) < N + 1; i++) {
            memo[i * (2 * i - 1)] = 1;
            hexNumbers.add(i * (2 * i - 1));
        }
        for (int i = 1; i < N + 1; i++) {
            fillMemo(i);
        }
        System.out.println(memo[N]);
    }
    static void fillMemo(int k) {
        if (memo[k] != 0) {
            return;
        }
        int min = Integer.MAX_VALUE;
        for (Integer i:hexNumbers) {
            if (i > k) break;
            min = Math.min(min, memo[i] + memo[k - i]);
        }
        memo[k] = min;
    }
}