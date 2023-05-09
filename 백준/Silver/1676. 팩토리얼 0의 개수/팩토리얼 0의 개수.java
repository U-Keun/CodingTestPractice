import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> storage = new Stack<>();
        int zeros = 0;
        int number;
        if (N == 0) {
            System.out.println(zeros);
        } else {
            for (int i = 1; i <= N; i++) {
                number = i;
                while (number % 2 == 0) {
                    number = number / 2;
                    storage.push(2);
                }
                while (number % 5 == 0) {
                    number = number / 5;
                    storage.pop();
                    zeros++;
                }
            }
            System.out.println(zeros);
        }
    }
}