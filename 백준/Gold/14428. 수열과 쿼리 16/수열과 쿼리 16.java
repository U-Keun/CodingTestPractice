import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static int[] tree, array;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            array = new int[N + 1];
            array[0] = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) array[i] = Integer.parseInt(st.nextToken());
            int h = (int) Math.ceil(Math.log(N) / Math.log(2));
            int treeLength = 1 << (h + 1);
            tree = new int[treeLength];
            generate(1, 1, N);
            int M = Integer.parseInt(br.readLine());
            while (M > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    update(1, 1, N, b, c);
                } else {
                    bw.write(minimalIndex(1, 1, N, b, c) + "\n");
                }
                M--;
            }
            bw.flush();
        }
    }
    private static int generate(int current, int start, int end) {
        if (start == end) return tree[current] = start;
        int mid = (start + end) >> 1;
        int left = generate(current * 2, start, mid);
        int right = generate(current * 2 + 1, mid + 1, end);
        return tree[current] = array[left] <= array[right] ? left : right;
    }
    private static int minimalIndex(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return tree[current];
        int mid = (start + end) >> 1;
        int leftHalf = minimalIndex(current * 2, start, mid, left, right);
        int rightHalf = minimalIndex(current * 2 + 1, mid + 1, end, left, right);
        return array[leftHalf] <= array[rightHalf] ? leftHalf : rightHalf;
    }
    private static void update(int current, int start, int end, int index, int newNumber) {
        if (index < start || index > end) return;
        if (start == end) {
            array[start] = newNumber;
        } else {
            int mid = (start + end) >> 1;
            update(current * 2, start, mid, index, newNumber);
            update(current * 2 + 1, mid + 1, end, index, newNumber);
            tree[current] = array[tree[current * 2]] <= array[tree[current * 2 + 1]] ? tree[current * 2] : tree[current * 2 + 1];
        }
    }
}