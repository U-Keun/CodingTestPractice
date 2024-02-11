import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        tree = new long[N + 1];
        long[] numbers = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
            update(i, numbers[i]);
        }
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            int left = Math.min(x, y), right = Math.max(x, y);
            print.append(getPrefixSum(left, right)).append("\n");
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            update(a, b - numbers[a]);
            numbers[a] = b;
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(print.toString());
        bw.flush();
        bw.close();
    }
    private static void update(int index, long diff) {
        while (index < N + 1) {
            tree[index] += diff;
            index += (index & -index);
        }
    }
    private static long getPrefixSum(int left, int right) {
       return sum(right) - sum(left - 1);
    }
    private static long sum(int index) {
        long answer = 0;
        while (index > 0) {
            answer += tree[index];
            index -= (index & -index);
        }
        return answer;
    }
}