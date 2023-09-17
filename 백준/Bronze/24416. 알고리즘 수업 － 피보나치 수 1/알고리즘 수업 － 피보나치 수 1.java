import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int countRecursion = 0, countDP = 0;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        br.close();
        recursion(N);
        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= N; i++) {
            countDP++;
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        StringBuilder print = new StringBuilder();
        print.append(countRecursion).append(" ").append(countDP);
        bw.write(print.toString());
        bw.flush();
        bw.close();
    }
    public static long recursion(long i) {
        if (i == 1 || i == 2) {
            countRecursion++;
            return 1;
        }
        return recursion(i - 1) + recursion(i - 2);
    }
}