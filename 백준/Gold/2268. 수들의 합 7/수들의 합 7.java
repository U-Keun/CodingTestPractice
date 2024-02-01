import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] numbers;
    private static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new long[(int) Math.pow(2, height + 1)];
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 0) {
                int first = Integer.parseInt(st.nextToken()), second = Integer.parseInt(st.nextToken());
                print.append(sum(1, 0, N - 1, Math.min(first, second) - 1, Math.max(first, second) - 1))
                        .append("\n");
            } else {
                int index = Integer.parseInt(st.nextToken()), newNumber = Integer.parseInt(st.nextToken());
                modify(1, 0, N - 1, index - 1, newNumber);
                numbers[index - 1] = newNumber;
            }
        }
        System.out.println(print);
    }
    private static long sum(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return 0L;
        if (left <= start && right >= end) return tree[current];
        int mid = (start + end) >> 1;
        return sum(current * 2, start, mid, left, right)
                + sum(current * 2 + 1, mid + 1, end, left, right);
    }
    private static void modify(int current, int start, int end, int index, int newNumber) {
        if (index < start || index > end) return;
        tree[current] = tree[current] + newNumber - numbers[index];
        if (start != end) {
            int mid = (start + end) / 2;
            modify(current * 2, start, mid, index, newNumber);
            modify(current * 2 + 1, mid + 1, end, index, newNumber);
        }
    }
}