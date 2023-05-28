import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long N, answer;
    static List<Long> divisors;
    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        answer = -1;
        divisors = new ArrayList<>();
        for (long i = 0; i * i <= N; i++) {
            if (N % (i + 1) == 0) {
                divisors.add(i + 1);
                if (i + 1 != N / (i + 1)) divisors.add(N / (i + 1));
            }
        }
        divisors.sort(Comparator.reverseOrder());
        for (long i:divisors) {
            if (i * phi(i) == N) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
    static long phi(long N) {
        long answer = N;
        for (long p = 2; p * p <= N; p++) {
            if (N % p == 0) {
                while (N % p == 0) N /= p;
                answer = answer * (p - 1) / p;
            }
        }
        if (N > 1) answer = answer * (N - 1) / N;
        return answer;
    }
}