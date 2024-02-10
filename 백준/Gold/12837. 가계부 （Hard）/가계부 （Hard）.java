import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new long[2 << height];
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            if (q == 1) {
                update(1, 0, N - 1,
                        Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
            } else {
                bw.write(getPrefixSum(1, 0, N - 1,
                        Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1) + "\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
    private static void update(int current, int start, int end, int index, int money) {
        if (index < start || index > end) return;
        tree[current] += money;
        if (start != end) {
            int mid = (start + end) >> 1;
            update(current * 2, start, mid, index, money);
            update(current * 2 + 1, mid + 1, end, index, money);
        }
    }
    private static long getPrefixSum(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return 0L;
        if (left <= start && right >= end) return tree[current];
        int mid = (start + end) >> 1;
        return getPrefixSum(current * 2, start, mid, left, right) +
                getPrefixSum(current * 2 + 1, mid + 1, end, left, right);
    }
}