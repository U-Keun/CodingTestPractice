import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> absHeap = new PriorityQueue<>(Comparator
                    .comparingInt(a -> Math.abs((Integer) a)).thenComparingInt(a -> (int) a));
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(br.readLine());
                if (x != 0) absHeap.add(x);
                else {
                    if (absHeap.isEmpty()) print.append(0).append('\n');
                    else print.append(absHeap.poll()).append('\n');
                }
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
}