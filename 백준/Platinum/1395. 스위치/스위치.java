import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] tree;
    private static boolean[] lazy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new int[2 << height];
        lazy = new boolean[2 << height];
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken()),
                    s = Integer.parseInt(st.nextToken()),
                    t = Integer.parseInt(st.nextToken());
            if (q == 0) {
                updateTree(1, 1, N, s, t);
            } else {
                print.append(countOn(1, 1, N, s, t)).append("\n");
            }
        }
        System.out.println(print);
    }
    private static void updateTree(int current, int start, int end, int left, int right) {
        updateLazy(current, start, end);
        if (right < start || left > end) return;
        if (left <= start && right >= end) {
            reverseAndDelay(current, start, end);
            return;
        }
        int mid = (start + end) >> 1;
        updateTree(current * 2, start, mid, left, right);
        updateTree(current * 2 + 1, mid + 1, end, left, right);
        tree[current] = tree[current * 2] + tree[current * 2 + 1];
    }
    private static int countOn(int current, int start, int end, int left, int right) {
        updateLazy(current, start, end);
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) return tree[current];
        int mid = (start + end) >> 1;
        return countOn(current * 2, start, mid, left, right) +
                countOn(current * 2 + 1, mid + 1, end, left, right);
    }
    private static void updateLazy(int current, int start, int end) {
        if (lazy[current]) {
            reverseAndDelay(current, start, end);
            lazy[current] = false;
        }
    }
    private static void reverseAndDelay(int current, int start, int end) {
        tree[current] = (end - start + 1) - tree[current];
        if (start != end) {
            lazy[current * 2] = !lazy[current * 2];
            lazy[current * 2 + 1] = !lazy[current * 2 + 1];
        }
    }
}