import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Stack<String> password = new Stack<>();
        Stack<String> tmp = new Stack<>();
        for (int i = 0; i < N; i++){
            String[] input = br.readLine().split("");
            for (String s: input) {
                if (password.isEmpty()) {
                    switch (s) {
                        case "<": case "-":
                            break;
                        case ">":
                            if (tmp.isEmpty()) {
                                break;
                            } else {
                                password.push(tmp.pop());
                                break;
                            }
                        default:
                            password.push(s);
                            break;
                    }
                } else {
                    switch (s) {
                        case "<":
                            tmp.push(password.pop());
                            break;
                        case ">":
                            if (tmp.isEmpty()) {
                                break;
                            } else {
                                password.push(tmp.pop());
                                break;
                            }
                        case "-":
                            password.pop();
                            break;
                        default:
                            password.push(s);
                            break;
                    }
                }
            }
            while (tmp.size() != 0) {
                password.push(tmp.pop());
            }
            for (int j = 0; j < password.size(); j++) {
                print.append(password.get(j));
            }
            if (i != N-1) print.append('\n');
            password.clear();
            tmp.clear();
        }
        System.out.println(print);
    }
}