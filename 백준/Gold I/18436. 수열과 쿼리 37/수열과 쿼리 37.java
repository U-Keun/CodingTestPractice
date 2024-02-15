import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new int[2 << height][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken()) % 2;
        }
        generate(numbers, 1, 0, N - 1);
        int M = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            if (q == 1) {
                update(1, 0, N - 1, a - 1, b % 2);
                numbers[a - 1] = b % 2;
            } else if (q == 2) {
                print.append(countEvenNumbers(1, 0, N - 1, a - 1, b - 1)).append("\n");
            } else {
                print.append(countOddNumbers(1, 0, N - 1, a - 1, b - 1)).append("\n");
            }
        }
        br.close();
        System.out.println(print);
    }
    private static int[] numbers;
    private static int[][] tree;
    private static int[] generate(int[] array, int current, int start, int end) {
        if (start == end) {
            int[] node = new int[2];
            if (array[start] == 0) {
                node[0]++;
            } else node[1]++;
            return tree[current] = node;
        }
        int mid = (start + end) / 2;
        int[] left = generate(array, current * 2, start, mid),
                right = generate(array, current * 2 + 1, mid + 1, end);
        return tree[current] = new int[]{left[0] + right[0], left[1] + right[1]};
    }
    private static void update(int current, int start, int end, int index, int newParity) {
        if (index < start || index > end) return;
        if (numbers[index] != newParity) {
            if (newParity == 0) {
                tree[current][0]++;
                tree[current][1]--;
            } else {
                tree[current][0]--;
                tree[current][1]++;
            }
        }
        if (start != end) {
            int mid = (start + end) / 2;
            update(current * 2, start, mid, index, newParity);
            update(current * 2 + 1, mid + 1, end, index, newParity);
        }
    }
    private static int countEvenNumbers(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return tree[current][0];
        int mid = (start + end) >> 1;
        return countEvenNumbers(current * 2, start, mid, left, right) +
                countEvenNumbers(current * 2 + 1, mid + 1, end, left, right);
    }
    private static int countOddNumbers(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return tree[current][1];
        int mid = (start + end) >> 1;
        return countOddNumbers(current * 2, start, mid, left, right) +
                countOddNumbers(current * 2 + 1, mid + 1, end, left, right);
    }
}