import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            char[] input = br.readLine().toCharArray();
            Stack<Object> stack = new Stack<>();
            boolean isNotValid = false;
            for (char c:input) {
                switch (c) {
                    case '(':
                        stack.push(c);
                        break;
                    case '[':
                        stack.push(c);
                        break;
                    case ')':
                        if (!stack.isEmpty()) {
                            int value = 0;
                            while (!stack.isEmpty() && stack.peek() instanceof Integer) {
                                value += (int) stack.pop();
                            }
                            if (value == 0) value++;
                            if (!stack.isEmpty() && stack.peek() instanceof Character && (char) stack.peek() == '(') {
                                stack.pop();
                            } else isNotValid = true;
                            stack.push(value * 2);
                        } else isNotValid = true;
                        break;
                    case ']':
                        if (!stack.isEmpty()) {
                            int value = 0;
                            while (!stack.isEmpty() && stack.peek() instanceof Integer) {
                                value += (int) stack.pop();
                            }
                            if (value == 0) value++;
                            if (!stack.isEmpty() && stack.peek() instanceof Character && (char) stack.peek() == '[') {
                                stack.pop();
                            } else isNotValid = true;
                            stack.push(value * 3);
                        } else isNotValid = true;
                        break;
                }

                if (isNotValid) {
                    bw.write(String.valueOf(0));
                    bw.flush();
                    return;
                }
            }

            int answer = 0;
            while (!stack.isEmpty()) {
                Object tmp = stack.pop();
                if (tmp instanceof Character) {
                    bw.write(String.valueOf(0));
                    bw.flush();
                    return;
                }
                answer += (int) tmp;
            }
            bw.write(String.valueOf(answer));
            bw.flush();
        }
    }
}