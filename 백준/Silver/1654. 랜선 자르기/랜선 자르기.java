import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int K, N;
    static long left, right, mid;
    static int[] lines;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lines = new int[K];
        left = 1;
        right = 0;
        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
            right += lines[i];
        }
        right /= N;
        while (left < right) {
            mid = (left + (int) right) / 2;
            if (countLines(mid) >= N) left = mid + 1;
            else right = mid;
        }
        if (countLines(left) < N) System.out.println(left - 1);
        else if (countLines(left + 1) >= N) System.out.println(left + 1);
        else System.out.println(left);
    }
    static int countLines(long length) {
        int count = 0;
        for (int i:lines) {
            count += i / length;
        }
        return count;
    }
}