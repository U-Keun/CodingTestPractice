import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        PriorityQueue<Integer> pque = new PriorityQueue<>(Comparator.reverseOrder());
        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                if (pque.isEmpty()) print.append(0).append('\n');
                else print.append(pque.poll()).append('\n');
            } else pque.add(input);
        }
        System.out.println(print);
    }
}