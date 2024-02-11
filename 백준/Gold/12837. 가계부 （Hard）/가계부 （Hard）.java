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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            if (q == 1) {
                update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
                 bw.write(getPrefixSum(r) - getPrefixSum(l - 1) + "\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
    private static void update(int index, int money) {
        while (index < N + 1) {
            tree[index] += money;
            index += (index & -index);
        }
    }
    private static long getPrefixSum(int index) {
        long answer = 0;
        while (index > 0) {
            answer += tree[index];
            index -= (index & -index);
        }
        return answer;
    }
}