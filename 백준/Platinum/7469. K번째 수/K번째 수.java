import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] numbers;
    private static int[][] mergeSortTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        mergeSortTree = new int[(int) Math.pow(2, height + 1)][];
        generate(1, 0, n - 1);
        int a, b, c;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            int low = (int) -1e9, high = (int) 1e9;
            while (low < high) {
                int mid = (low + high) >> 1;
                int result = getCountSmallerThanX(1, 0, n - 1, a - 1, b - 1, mid);
                if (result < c) low = mid + 1;
                else high = mid;
            }
            print.append(low).append("\n");
        }
        System.out.println(print);
    }
    private static int[] generate(int current, int start, int end) {
        if (start == end) return mergeSortTree[current] = new int[]{numbers[start]};
        int mid = (start + end) >> 1;
        return mergeSortTree[current] = merge(generate(current * 2, start, mid), generate(current * 2 + 1, mid + 1, end));
    }
    private static int[] merge(int[] array1, int[] array2) {
        int length1 = array1.length, i = 0, length2 = array2.length, j = 0, index = 0;
        int[] merged = new int[length1 + length2];
        while (index < length1 + length2) {
            if (j == length2 || (i < length1 && array1[i] < array2[j])) {
                merged[index++] = array1[i++];
            }
            else {
                merged[index++] = array2[j++];
            }
        }
        return merged;
    }
    private static int getCountSmallerThanX(int current, int start, int end, int left, int right, int x) {
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return binarySearch(mergeSortTree[current], x);
        int mid = (start + end) >> 1;
        return getCountSmallerThanX(current * 2, start, mid, left, right, x) +
                getCountSmallerThanX(current * 2 + 1, mid + 1, end, left, right, x);
    }
    private static int binarySearch(int[] array, int key) {
        int low = 0, high = array.length;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (array[mid] <= key) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}