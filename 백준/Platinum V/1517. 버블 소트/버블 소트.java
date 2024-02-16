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
        br.close();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        generate(1, 0, N - 1);
        System.out.println(inversions);
    }
    private static int[] numbers;
    private static int[][] tree;
    private static long inversions = 0;
    private static int[] generate(int current, int start, int end) {
        if (start == end) return tree[current] = new int[]{numbers[start]};
        int mid = (start + end) >> 1;
        return tree[current] = merge(generate(current * 2, start, mid),
                generate(current * 2 + 1, mid + 1, end));
    }
    private static int[] merge(int[] array1, int[] array2) {
        int leftLength = array1.length, rightLength = array2.length;
        int i = 0, j = 0, index = 0;
        int[] merged = new int[leftLength + rightLength];
        while (i < leftLength && j < rightLength) {
            if (array1[i] <= array2[j]) {
                merged[index++] = array1[i++];
            } else {
                merged[index++] = array2[j++];
                inversions += leftLength - i;
            }
        }
        while (i < leftLength) merged[index++] = array1[i++];
        while (j < rightLength) merged[index++] = array2[j++];
        return merged;
    }
}