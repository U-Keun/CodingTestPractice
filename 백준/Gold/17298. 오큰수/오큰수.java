import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = readInt();
        }
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= input[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) answer[i] = -1;
            else answer[i] = stack.peek();
            stack.push(input[i]);
        }
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < n; i++) {
            print.append(answer[i]).append(" ");
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