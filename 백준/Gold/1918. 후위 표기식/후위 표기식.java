import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            char[] input = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();
            Map<Character, Integer> priority = new HashMap<>();
            priority.put('(', 1);
            priority.put('+', 2);
            priority.put('-', 2);
            priority.put('*', 3);
            priority.put('/', 3);
            for (char c : input) {
                if (c >= 'A' && c <= 'Z') {
                    bw.write(String.valueOf(c));
                } else {
                    if (stack.isEmpty()) {
                        stack.push(c);
                        continue;
                    }
                    switch (c) {
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                            while (!stack.isEmpty() && priority.get(c) <= priority.get(stack.peek())) {
                                bw.write(String.valueOf(stack.pop()));
                            }
                            stack.push(c);
                            break;
                        case '(':
                            stack.push(c);
                            break;
                        case ')':
                            while (stack.peek() != '(') {
                                bw.write(String.valueOf(stack.pop()));
                            }
                            stack.pop();
                            break;
                    }
                }
            }
            while (!stack.isEmpty()) {
                bw.write(String.valueOf(stack.pop()));
            }
            bw.flush();
        }
    }
}