import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] numbers;
    private static int[] minimumTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        numbers = new int[N];
        long[] prefix = new long[N + 1];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            prefix[i + 1] += prefix[i] + numbers[i];
        }
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        minimumTree = new int[2 << height];
        generateMinimumTree(1, 0, N - 1);
        long answer = 0;
        for (int i = 0; i < N; i++) {
            int rightLow = i + 1, rightHigh = N;
            while (rightLow < rightHigh) {
                int mid = (rightLow + rightHigh) >> 1;
                if (numbers[i] > getMinimum(1, 0, N - 1, i + 1, mid)) rightHigh = mid;
                else rightLow = mid + 1;
            }
            int leftLow = 0, leftHigh = i;
            while (leftLow < leftHigh) {
                int mid = (leftLow + leftHigh) >> 1;
                if (numbers[i] > getMinimum(1, 0, N - 1, mid, i)) leftLow = mid + 1;
                else leftHigh = mid;
            }
            answer = Math.max(answer, numbers[i] * (prefix[rightLow] - prefix[leftLow]));
        }
        System.out.println(answer);

    }
    private static int generateMinimumTree(int current, int start, int end) {
        if (start == end) return minimumTree[current] = numbers[start];
        int mid = (start + end) >> 1;
        return minimumTree[current] = Math.min(generateMinimumTree(current * 2, start, mid),
                generateMinimumTree(current * 2 + 1, mid + 1, end));
    }
    private static int getMinimum(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return Integer.MAX_VALUE;
        if (left <= start && right >= end) return minimumTree[current];
        int mid = (start + end) >> 1;
        return Math.min(getMinimum(current * 2, start, mid, left, right),
                getMinimum(current * 2 + 1, mid + 1, end, left, right));
    }
}