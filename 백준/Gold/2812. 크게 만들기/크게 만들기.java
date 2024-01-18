import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken()), length = N - K;
        Stack<Integer> stack = new Stack<>();
        char[] input = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            int number = input[i] - '0';
            while (!stack.isEmpty() && K > 0 && stack.peek() < number) {
                stack.pop();
                K--;
            }
            stack.push(number);
        }
        while (stack.size() > length) stack.pop();
        StringBuilder print = new StringBuilder();
        for (Integer number : stack) {
            print.append(number);
        }
        System.out.println(print);
    }
}