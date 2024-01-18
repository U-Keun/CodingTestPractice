import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = readInt();
        }
        Stack<Integer> stack = new Stack<>(), indexStack = new Stack<>();
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
                indexStack.pop();
            }
            if (!stack.isEmpty()) {
                answer[i] = indexStack.peek();
            }
            stack.push(heights[i]);
            indexStack.push(i + 1);
        }
        StringBuilder print = new StringBuilder();
        for (int number : answer) {
            print.append(number).append(" ");
        }
        System.out.println(print);

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