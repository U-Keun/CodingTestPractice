import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static long[] tree, lazy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new long[2 << height];
        lazy = new long[2 << height];
        long[] numbers = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }
        generate(numbers, 1, 1, N);
        int M = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        for (int l = 0; l < M; l++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            if (q == 1) {
                int i = Integer.parseInt(st.nextToken()), j = Integer.parseInt(st.nextToken());
                long k = Long.parseLong(st.nextToken());
                update(1, 1, N, i, j, k);
            } else {
                int x = Integer.parseInt(st.nextToken());
                print.append(getValue(1, 1, N, x)).append("\n");
            }
        }
        br.close();
        System.out.println(print);
    }
    private static void generate(long[] numbers, int current, int start, int end) {
        if (start == end) {
            tree[current] = numbers[start];
            return;
        }
        int mid = (start + end) >> 1;
        generate(numbers, current * 2, start, mid);
        generate(numbers, current * 2 + 1, mid + 1, end);
    }
    private static void update(int current, int start, int end, int left, int right, long diff) {
        updateLazy(current, start, end);
        if (right < start || left > end) return;
        if (left <= start && right >= end) {
            if (start == end) {
                tree[current] += diff;
            } else {
                lazy[current * 2] += diff;
                lazy[current * 2 + 1] += diff;
            }
            return;
        }
        int mid = (start + end) >> 1;
        update(current * 2, start, mid, left, right, diff);
        update(current * 2 + 1, mid + 1, end, left, right, diff);
    }
    private static long getValue(int current, int start, int end, int index) {
        updateLazy(current, start, end);
        if (index > end || index < start) return 0L;
        if (start == end) return tree[current];
        int mid = (start + end) >> 1;
        return getValue(current * 2, start, mid, index) +
                getValue(current * 2 + 1, mid + 1, end, index);
    }
    private static void updateLazy(int current, int start, int end) {
        if (lazy[current] != 0) {
            if (start == end) {
                tree[current] += lazy[current];
            } else {
                lazy[current * 2] += lazy[current];
                lazy[current * 2 + 1] += lazy[current];
            }
            lazy[current] = 0;
        }
    }
}