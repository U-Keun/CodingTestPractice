import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, count;
    static String[] N, M;
    static Stack<String> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            count = 0;
            st = new StringTokenizer(br.readLine());
            N = st.nextToken().split("");
            M = st.nextToken().split("");
            for (int j = 0; j < N.length; j++) {
                if (N[j].equals(M[j])) continue;
                if (stack.isEmpty()) stack.add(N[j]);
                else {
                    if (!stack.peek().equals(N[j])) {
                        stack.pop();
                        count++;
                    } else stack.add(N[j]);
                }
            }
            if (!stack.isEmpty()) {
                count += stack.size();
                stack.clear();
                System.out.println(count);
            } else System.out.println(count);
        }
    }
}