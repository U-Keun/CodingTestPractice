import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] buckets;
    static int sum(int[] arr) {
        int sum = 0;
        for (int i:arr) sum += i;
        return sum;
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        buckets = new int[K];
        for (int i = 0; i < K; i++) {
            buckets[i] = i + 1;
        }
        while (sum(buckets) < N) {
            for (int i = 0; i < K; i++) {
                if (i != K - 1 && buckets[i] + 1 != buckets[i + 1]) {
                    buckets[i]++;
                    break;
                }
                else if (i == K - 1) {
                    buckets[i]++;
                    break;
                }
            }
        }
        if (sum(buckets) == N) System.out.println(buckets[K - 1] - buckets[0]);
        else System.out.println(-1);
    }
}