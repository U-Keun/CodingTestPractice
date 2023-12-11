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
            String input = br.readLine();
            while (!input.equals("END")) {
                Stack<Character> stack = new Stack<>();
                for (char c : input.toCharArray()) {
                    stack.push(c);
                }
                while (!stack.isEmpty()) {
                    bw.write(stack.pop());
                }
                bw.write("\n");
                input = br.readLine();
            }
            bw.flush();
        }
    }
}