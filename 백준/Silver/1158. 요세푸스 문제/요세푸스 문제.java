import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        print.append("<");
        Queue<Integer> cycle = new LinkedList<>();
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        for (int i=0; i<N; i++) {
            cycle.add(i+1);
        }
        int K = Integer.parseInt(input[1]);
        while (!cycle.isEmpty()) {
            for (int i=1; i<K; i++) {
                cycle.add(cycle.poll());
            }
            if (cycle.size()==1) print.append(cycle.poll());
            else print.append(cycle.poll()).append(", ");
        }
        print.append(">");
        System.out.println(print);
    }
}