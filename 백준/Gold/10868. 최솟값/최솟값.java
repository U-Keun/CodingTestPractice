import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static long[] array, tree;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
            array = new long[N];
            for (int i = 0; i < N; i++) array[i] = Long.parseLong(br.readLine());
            int h = (int) Math.ceil(Math.log(N) / Math.log(2));
            tree = new long[(int) Math.pow(2, h + 1)];
            generate(1, 0, N - 1);
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
                print.append(getMinimum(1, 0, N - 1, a - 1, b - 1)).append('\n');
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
    public static long generate(int current, int start, int end) {
        if (start == end) return tree[current] = array[start];
        int mid = (start + end) / 2;
        return tree[current] = Math.min(generate(current * 2, start, mid),
                generate(current * 2 + 1, mid + 1, end));
    }
    public static long getMinimum(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return Long.MAX_VALUE;
        if (left <= start && right >= end) return tree[current];
        int mid = (start + end) / 2;
        return Math.min(getMinimum(current * 2, start, mid, left, right),
                getMinimum(current * 2 + 1, mid + 1, end, left, right));
    }
}