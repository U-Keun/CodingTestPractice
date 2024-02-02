import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] numbers;
    private static int[][] mergeSortTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        mergeSortTree = new int[(int) Math.pow(2, height + 1)][];
        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        generate(1, 0, N - 1);
        int M = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        int i, j, k, answer = 0;
        for (int a = 0; a < M; a++) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken()) ^ answer;
            j = Integer.parseInt(st.nextToken()) ^ answer;
            k = Integer.parseInt(st.nextToken()) ^ answer;
            answer = countTargets(1, 0, N - 1, i - 1, j - 1, k);
            print.append(answer);
            print.append("\n");
        }
        br.close();
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
    private static int countTargets(int current, int start, int end, int left, int right, int target) {
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return mergeSortTree[current].length - binarySearch(mergeSortTree[current], target);
        int mid = (start + end) >> 1;
        return countTargets(current * 2, start, mid, left, right, target)
                + countTargets(current * 2 + 1, mid + 1, end, left, right, target);
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