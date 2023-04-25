import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> points = new TreeMap<>();
        String[] input;
        int left, right;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            left = Integer.parseInt(input[0]) - Integer.parseInt(input[1]);
            right = Integer.parseInt(input[0]) + Integer.parseInt(input[1]);
            points.put(left, i);
            points.put(right, i);
        }
        Stack<Integer> checker = new Stack<>();
        if (points.size() != 2 * N) {
            System.out.println("NO");
        } else {
            for (int k:points.values()) {
                if (checker.isEmpty()) checker.push(k);
                else {
                    if (Objects.equals(checker.peek(), k)) checker.pop();
                    else checker.push(k);
                }
            }
            if (!checker.isEmpty()) System.out.println("NO");
            else System.out.println("YES");
        }

    }
}