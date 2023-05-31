import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long A, B, k, r1, r2;
    static Queue<Long> numbers;
    static int count;
    static boolean found;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        numbers = new LinkedList<>();
        numbers.add(A);
        count = 1;
        found = false;
        Loop : while (!numbers.isEmpty()) {
            count++;
            int n = numbers.size();
            for (long i = 0; i < n; i++) {
                k = numbers.poll();
                r1 = 2 * k;
                r2 = 10 * k + 1;
                if (r1 == B || r2 == B) {
                    found = true;
                    break Loop;
                }
                if (r1 < B) numbers.add(r1);
                if (r2 < B) numbers.add(r2);
            }
        }
        if (found) System.out.println(count);
        else System.out.println(-1);
    }
}