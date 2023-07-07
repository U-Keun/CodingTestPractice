import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, left, right, t, l;
    static Queue<Integer[]> memo = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        left = Math.min(a, b);
        right = Math.max(a, b);
        m = Integer.parseInt(br.readLine());
        memo.add(new Integer[]{left, right, 0});
        for (int i = 1; i < m + 1; i++) {
            t = Integer.parseInt(br.readLine());
            l = memo.size();
            for (int j = 0; j < l; j++) {
                Integer[] cases = memo.poll();
                if (t <= cases[0]) {
                    memo.add(new Integer[]{t, cases[1], cases[2] + cases[0] - t});
                } else if (t >= cases[1]) {
                    memo.add(new Integer[]{cases[0], t, cases[2] + t - cases[1]});
                } else {
                    memo.add(new Integer[]{t, cases[1], cases[2] + t - cases[0]});
                    memo.add(new Integer[]{cases[0], t, cases[2] + cases[1] - t});
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (Integer[] record:memo) {
            if (record[2] < min) min = record[2];
        }
        System.out.println(min);
    }
}