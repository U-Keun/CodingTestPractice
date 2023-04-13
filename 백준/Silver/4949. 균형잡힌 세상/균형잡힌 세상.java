import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        Stack<String> pth = new Stack<>();

        String[] sentence;
        Loop:
        do {
            sentence = br.readLine().split("");
            if (sentence.length == 1) break;
            for (String s : sentence) {
                if ((s.charAt(0) >= 'a' && s.charAt(0) <= 'z') ||
                        (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z') ||
                        s.equals(" ") || s.equals(".")) continue;
                else {
                    switch (s) {
                        case "(":
                            pth.push(s);
                            break;
                        case ")":
                            if (pth.isEmpty() || pth.peek().equals("[")) {
                                print.append("no").append('\n');
                                pth.clear();
                                continue Loop;
                            } else pth.pop();
                            break;
                        case "[":
                            pth.push(s);
                            break;
                        case "]":
                            if (pth.isEmpty() || pth.peek().equals("(")) {
                                print.append("no").append('\n');
                                pth.clear();
                                continue Loop;
                            } else pth.pop();
                            break;
                    }
                }
            }
            if (pth.size() > 0) {
                print.append("no").append('\n');
                pth.clear();
            } else print.append("yes").append('\n');
        } while (!sentence[0].equals("."));
        System.out.println(print);
    }
}