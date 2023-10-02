import java.io.*;
import java.util.*;

public class Main {
    static long[] tree;
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
            tree = new long[(int) Math.pow(2, h + 1)];
            generate(array, 1, 0, N - 1);
            StringBuilder print = new StringBuilder();
            while (M + K > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    long c = Long.parseLong(st.nextToken());
                    update(1, 0, N - 1, b - 1, c - array[b - 1]);
                    array[b - 1] = c;
                    M--;
                } else {
                    int c = Integer.parseInt(st.nextToken());
                    print.append(prefixSum(1, 0, N - 1, b - 1, c - 1)).append('\n');
                    K--;
                }
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
    public static long generate(long[] arr, int current, int start, int end) {
        if (start == end) return tree[current] = arr[start];
        return tree[current] = generate(arr, current * 2, start, (start + end) / 2)
                + generate(arr, current * 2 + 1, (start + end) / 2 + 1, end);
    }
    public static long prefixSum(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return tree[current];
        return prefixSum(current * 2, start, (start + end) / 2, left, right)
                + prefixSum(current * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }
    public static void update(int current, int start, int end, int idx, long diff) {
        if (idx < start || idx > end) return;
        tree[current] += diff;
        if (start != end) {
            update(current * 2, start, (start + end) / 2, idx, diff);
            update(current * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
        }
    }
}