import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] numbers, segmentTree;
    private static final int MAX = 65537;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        int height = (int) Math.ceil(Math.log(MAX) / Math.log(2));
        segmentTree = new int[2 << height];
        numbers = new int[N];
        long answer = 0;
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            update(1, 0, MAX, numbers[i], 1);
            if (i >= K - 1) {
                if (i > K - 1) update(1, 0, MAX, numbers[i - K], -1);
                answer += getMedium(1, 0, MAX, (K + 1) / 2);
            }
        }
        br.close();
        System.out.println(answer);
    }
    private static void update(int current, int start, int end, int number, int count) {
        if (number < start || number > end) return;
        segmentTree[current] += count;
        if (start == end) return;
        int mid = (start + end) >> 1;
        update(current * 2, start, mid, number, count);
        update(current * 2 + 1, mid + 1, end, number, count);
    }
    private static int getMedium(int current, int start, int end, int count) {
        if (start == end) return start;
        int mid = (start + end) >> 1;
        if (segmentTree[current * 2] >= count) {
            return getMedium(current * 2, start, mid, count);
        } else return getMedium(current * 2 + 1, mid + 1, end, count - segmentTree[current * 2]);
    }
}