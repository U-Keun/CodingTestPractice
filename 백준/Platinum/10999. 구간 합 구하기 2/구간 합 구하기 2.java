import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static long[] tree, lazy;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()),
                    M = Integer.parseInt(st.nextToken()),
                    K = Integer.parseInt(st.nextToken());
            long[] array = new long[N];
            for (int i = 0; i < N; i++) array[i] = Long.parseLong(br.readLine());
            int h = (int) Math.ceil(Math.log(N) / Math.log(2));
            int treeLength = 1 << (h + 1);
            tree = new long[treeLength];
            lazy = new long[treeLength];
            generate(array, 1, 0, N - 1);
            while (M + K > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    long d = Long.parseLong(st.nextToken());
                    update(1, 0, N - 1, b - 1, c - 1, d);
                    M--;
                } else {
                    bw.write(prefixSum(1, 0, N - 1, b - 1, c - 1) + "\n");
                    K--;
                }

            }
            bw.flush();
        }
    }
    private static long generate(long[] arr, int current, int start, int end) {
        if (start == end) return tree[current] = arr[start];
        return tree[current] = generate(arr, current * 2, start, (start + end) / 2)
                + generate(arr, current * 2 + 1, (start + end) / 2 + 1, end);
    }
    private static long prefixSum(int current, int start, int end, int left, int right) {
        updateLazy(current, start, end);
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return tree[current];
        return prefixSum(current * 2, start, (start + end) / 2, left, right)
                + prefixSum(current * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }
    private static void update(int current, int start, int end, int left, int right, long diff) {
        updateLazy(current, start, end);
        if (right < start || left > end) return;
        if (left <= start && right >= end) {
            tree[current] += (end - start + 1) * diff;
            if (start != end) {
                lazy[current * 2] += diff;
                lazy[current * 2 + 1] += diff;
            }
            return;
        }
        update(current * 2, start, (start + end) / 2, left, right, diff);
        update(current * 2 + 1, (start + end) / 2 + 1, end, left, right, diff);
        tree[current] = tree[current * 2] + tree[current * 2 + 1];
    }
    private static void updateLazy(int current, int start, int end) {
        if (lazy[current] != 0) {
            tree[current] += (end - start + 1) * lazy[current];
            if (start != end) {
                lazy[current * 2] += lazy[current];
                lazy[current * 2 + 1] += lazy[current];
            }
            lazy[current] = 0;
        }
    }
}