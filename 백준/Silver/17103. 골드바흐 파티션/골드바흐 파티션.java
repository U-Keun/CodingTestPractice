import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine()), count = 0;
            if (N == 4) {
                bw.write(1 + "\n");
                continue;
            }
            boolean[] isPrime = new boolean[N + 1];
            sieve(isPrime);
            for (int j = 3; j + j <= N; j++) {
                if (!isPrime[j] && !isPrime[N - j]) count++;
            }
            bw.write(count + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    static void sieve(boolean[] isPrime) { // Sieve of Eratosthenes
        int n = isPrime.length;
        for (int i = 2; i <= n - 1; i++) {
            if (!isPrime[i]) {
                for (int j = i + i; j <= n - 1; j += i) {
                    isPrime[j] = true;
                }
            }
        }
    }
}