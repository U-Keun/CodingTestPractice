import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String input;
    static Stack<Character> pth;
    static int answer;

    public static void main(String[] args) throws IOException {
        input = br.readLine();
        input = input.replace("()", ".");
        pth = new Stack<>();
        answer = 0;
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '.':
                    answer += pth.size();
                    break;
                case '(':
                    pth.add('(');
                    break;
                case ')':
                    pth.pop();
                    answer++;
                    break;
            }
        }
        System.out.println(answer);
    }
}