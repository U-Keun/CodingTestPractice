import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static int[] tree;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            int[] array = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) array[i] = Integer.parseInt(st.nextToken());
            int h = (int) Math.ceil(Math.log(N) / Math.log(2));
            int treeLength = 1 << (h + 1);
            tree = new int[treeLength];
            generate(array, 1, 0, N - 1);
            int M = Integer.parseInt(br.readLine());
            while (M > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    update(1, 0, N - 1, b - 1, c);
                } else {
                    bw.write(minimum(1, 0, N - 1, b - 1, c - 1) + "\n");
                }
                M--;
            }
            bw.flush();
        }
    }
    private static int generate(int[] arr, int current, int start, int end) {
        if (start == end) return tree[current] = arr[start];
        return tree[current] = Math.min(generate(arr, current * 2, start, (start + end) / 2)
                , generate(arr, current * 2 + 1, (start + end) / 2 + 1, end));
    }
    private static int minimum(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return Integer.MAX_VALUE;
        if (left <= start && right >= end) return tree[current];
        return Math.min(minimum(current * 2, start, (start + end) / 2, left, right)
                , minimum(current * 2 + 1, (start + end) / 2 + 1, end, left, right));
    }
    private static void update(int current, int start, int end, int index, int newNumber) {
        if (index < start || index > end) return;
        if (start == end) tree[current] = newNumber;
        else {
            int mid = (start + end) >> 1;
            update(current * 2, start, mid, index, newNumber);
            update(current * 2 + 1, mid + 1, end, index, newNumber);
            tree[current] = Math.min(tree[current * 2], tree[current * 2 + 1]);
        }
    }
}