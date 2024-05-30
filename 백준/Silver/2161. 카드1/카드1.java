import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.addLast(i);
        }
        StringBuilder print = new StringBuilder();
        while (deque.size() > 1) {
            print.append(deque.pollFirst()).append(" ");
            deque.addLast(deque.pollFirst());
        }
        print.append(deque.pollFirst());
        System.out.println(print);
    }
}