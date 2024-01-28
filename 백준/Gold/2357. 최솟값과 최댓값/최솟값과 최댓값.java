import java.io.IOException;

public class Main {
    private static int[] minTree, maxTree;
    private static int[] numbers;
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = readInt();
        }
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        minTree = new int[(int) Math.pow(2, height + 1)];
        maxTree = new int[(int) Math.pow(2, height + 1)];
        generateTree(1, 0, N - 1);

        StringBuilder print = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int a = readInt(), b = readInt();
            int[] answer = getIntervalValue(1, 0, N - 1, a - 1, b - 1);
            print.append(answer[0]).append(" ").append(answer[1]).append("\n");
        }
        System.out.println(print);
    }
    private static int[] generateTree(int current, int start, int end) {
        int[] answer = new int[2];
        if (start == end) {
            minTree[current] = numbers[start];
            maxTree[current] = numbers[start];
            answer[0] = minTree[current];
            answer[1] = maxTree[current];
            return answer;
        }
        int mid = (start + end) >> 1;
        int[] left = generateTree(current * 2, start, mid), right = generateTree(current * 2 + 1, mid + 1, end);
        minTree[current] = Math.min(left[0], right[0]);
        maxTree[current] = Math.max(left[1], right[1]);
        answer[0] = minTree[current];
        answer[1] = maxTree[current];
        return answer;
    }
    private static int[] getIntervalValue(int current, int start, int end, int left, int right) {
        int[] answer = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        if (left > end || right < start) return answer;
        if (left <= start && right >= end) {
            answer[0] = minTree[current];
            answer[1] = maxTree[current];
            return answer;
        }
        int mid = (start + end) >> 1;
        int[] leftHalf = getIntervalValue(current * 2, start, mid, left, right), rightHalf = getIntervalValue(current * 2 + 1, mid + 1, end, left, right);
        answer[0] = Math.min(leftHalf[0], rightHalf[0]);
        answer[1] = Math.max(leftHalf[1], rightHalf[1]);
        return answer;
    }
    private static int readInt() throws IOException {
        int c, n;
        boolean isNegative = false;
        c = System.in.read();
        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }
        n = c & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return isNegative ? -n : n;
    }
}