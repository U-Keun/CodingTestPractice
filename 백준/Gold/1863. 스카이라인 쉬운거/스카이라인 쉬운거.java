import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int x = readInt(), y = readInt();
            while (stack.peek() > y) {
                stack.pop();
                answer++;
            }
            if (stack.peek() == y) continue;
            stack.push(y);
        }
        while (!stack.isEmpty()) {
            if (stack.peek() > 0) {
                stack.pop();
                answer++;
            } else break;
        }
        System.out.println(answer);
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
}