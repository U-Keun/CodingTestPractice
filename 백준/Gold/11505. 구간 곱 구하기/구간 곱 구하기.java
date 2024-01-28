import java.io.IOException;

public class Main {
    private static long[] tree;
    private static int[] numbers;
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt(), K = readInt();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = readInt();
        }
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new long[(int) Math.pow(2, height + 1)];
        generateTree(1, 0, N - 1);

        StringBuilder print = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            int a = readInt(), b = readInt(), c = readInt();
            if (a == 1) {
                update(1, 0, N - 1, b - 1, c);
            } else {
                print.append(getIntervalValue(1, 0, N - 1, b - 1, c - 1)).append("\n");
            }
        }
        System.out.println(print);
    }
    private static long generateTree(int current, int start, int end) {
        if (start == end) return tree[current] = numbers[start];
        int mid = (start + end) >> 1;
        return tree[current] = (generateTree(current * 2, start, mid)
                * generateTree(current * 2 + 1, mid + 1, end)) % MOD;
    }
    private static long update(int current, int start, int end, int idx, int newNumber) {
        if (idx < start || idx > end) return tree[current];
        if (start == end) return tree[current] = newNumber;
        int mid = (start + end) >> 1;
        return tree[current] = (update(current * 2, start, mid, idx, newNumber)
                * update(current * 2 + 1, mid + 1, end, idx, newNumber)) % MOD;
    }
    private static long getIntervalValue(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return 1L;
        if (left <= start && right >= end) return tree[current];
        int mid = (start + end) >> 1;
        return (getIntervalValue(current * 2, start, mid, left, right)
                * getIntervalValue(current * 2 + 1, mid + 1, end, left, right)) % MOD;
    }
    private static int readInt() throws IOException {
        int c, n;
        boolean isNegative = false;
        c = System.in.read();
        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }
        n = c & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return isNegative ? -n : n;
    }
}