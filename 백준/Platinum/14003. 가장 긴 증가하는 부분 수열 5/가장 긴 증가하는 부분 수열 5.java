import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] input = new int[N], sequence = new int[N], maxLength = new int[N];
            for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(sequence, Integer.MAX_VALUE);
            sequence[0] = input[0];
            maxLength[0] = 1;
            int answer = 0;
            for (int i = 1; i < N; i++) {
                if (sequence[answer] < input[i]) {
                    sequence[++answer] = input[i];
                    maxLength[i] = answer + 1;
                } else {
                    int idx = binarySearch(sequence, answer, input[i]);
                    sequence[idx] = input[i];
                    maxLength[i] = idx + 1;
                }
            }
            StringBuilder print = new StringBuilder();
            print.append(answer + 1).append('\n');
            Stack<Integer> stack = new Stack<>();
            int maxLengthIdx = N - 1;
            while (maxLengthIdx >= 0 && answer >= 0) {
                if (maxLength[maxLengthIdx] == answer + 1) {
                    stack.push(input[maxLengthIdx--]);
                    answer--;
                } else maxLengthIdx--;
            }
            while (!stack.isEmpty()) print.append(stack.pop()).append(" ");
            bw.write(print.toString());
            bw.flush();
        }

    }
    public static int binarySearch(int[] arr, int end, int key) {
        int start = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] < key) start = mid + 1;
            else end = mid;
        }
        return start;
    }
}