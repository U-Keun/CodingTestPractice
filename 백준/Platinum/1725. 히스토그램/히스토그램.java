import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder print = new StringBuilder();
            int n = Integer.parseInt(br.readLine());
            int[] histogram = new int[n];
            for (int i = 0; i < n; i++) {
                histogram[i] = Integer.parseInt(br.readLine());
            }
            Stack<Integer> stack = new Stack<>();
            int[] left = new int[n];
            for (int i = 0; i < n; i++) {
                int idx = i;
                while (!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
                    stack.pop();
                    idx = left[idx];
                }
                if (stack.isEmpty()) left[i] = 0;
                else left[i] = stack.peek() + 1;
                stack.push(i);
            }
            stack.clear();
            int[] right = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                int idx = i;
                while (!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
                    stack.pop();
                    idx = right[idx];
                }
                if (stack.isEmpty()) right[i] = n;
                else right[i] = stack.peek();
                stack.push(i);
            }
            long max = -1;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, (long) histogram[i] * (right[i] - left[i]));
            }
            print.append(max).append('\n');
            bw.write(print.toString());
            bw.flush();
        }
    }
}