import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long N;
    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        System.out.println(phi(N));
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
}