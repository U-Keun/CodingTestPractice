import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int N, M, k, lower, upper, mid;
    static int[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Loop : for (int i = 0; i< M; i++) {
            k = Integer.parseInt(st.nextToken());
            lower = 0;
            upper = N - 1;
            while (lower + 1 < upper) {
                mid = (lower + upper) / 2;
                if (check(lower) == check(upper)) {
                    if (A[lower] == k || A[upper] == k) {
                        print.append(1).append('\n');
                        continue Loop;
                    }
                    print.append(0).append('\n');
                    continue Loop;
                }
                if (check(lower) == check(mid)) lower = mid;
                else if (check(mid) == check(upper)) upper = mid;
            }
            if (A[lower] == k || A[upper] == k) {
                print.append(1).append('\n');
            } else print.append(0).append('\n');
        }
        System.out.println(print);
    }
    static boolean check(int idx) {
        return A[idx] > k;
    }
}