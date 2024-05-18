import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pque = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            pque.add(Integer.parseInt(br.readLine()));
        }
        br.close();
        StringBuilder print = new StringBuilder();
        while (!pque.isEmpty()) {
            print.append(pque.poll());
            print.append("\n");
        }
        System.out.println(print);
    }
}