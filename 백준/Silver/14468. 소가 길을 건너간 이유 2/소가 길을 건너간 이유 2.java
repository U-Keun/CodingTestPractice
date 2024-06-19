import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        br.close();

        int answer = 0;
        Deque<Character> stack = new ArrayDeque<>();
        boolean[] isInTheStack = new boolean[26];
        for (char c : input) {
            if (!isInTheStack[c - 'A']) {
                isInTheStack[c - 'A'] = true;
                stack.addLast(c);
                continue;
            }

            Deque<Character> subStack = new ArrayDeque<>();
            while (stack.peekLast() != c) {
                subStack.addLast(stack.pollLast());
                answer++;
            }
            stack.pollLast();

            while (!subStack.isEmpty()) {
                stack.addLast(subStack.pollLast());
            }
        }

        System.out.println(answer);
    }
}