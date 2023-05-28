import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static StringTokenizer st;
    static int T;
    static long N, M, k, answer;
    static Stack<Long> phiValues;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        phiValues = new Stack<>();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Long.parseLong(st.nextToken());
            M = Long.parseLong(st.nextToken());
            while (M != 1) {
                phiValues.add(M);
                M = phi(M);
            }
            answer = 0;
            while (!phiValues.isEmpty()) {
                k = phiValues.pop();
                answer = modPow(N, answer, k) + k;
            }
            if (answer == 0) print.append(answer).append('\n');
            else print.append(answer % k).append('\n');
        }
        System.out.println(print);
    }
    static long phi(long N) {
        long answer = N;
        for (long p = 2; p * p <= N; p++) {
            if (N % p == 0) {
                while (N % p == 0) N /= p;
                answer = answer / p * (p - 1);
            }
        }
        if (N > 1) answer = answer / N * (N - 1);
        return answer;
    }
    static long modPow(long N, long answer, long k) {
        long res = 1;
        N = N % k;
        while (answer > 0) {
            if (answer % 2 == 1) {
                res = (res * N) % k;
            }
            N = (N * N) % k;
            answer /= 2;
        }
        return res;
    }
}