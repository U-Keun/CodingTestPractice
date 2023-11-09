import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int A = Integer.parseInt(br.readLine());
            int T = Integer.parseInt(br.readLine());
            int target = Integer.parseInt(br.readLine());
            List<Integer> template = List.of(0, 1, 0, 1);
            Queue<Integer> queue = new LinkedList<>(template);
            int round = 2;
            fillQueue(queue, round);
            int count = -1;
            while (T > 0) {
                int tmp = queue.poll();
                if (tmp == target) {
                    T--;
                }
                count++;
                if (queue.isEmpty()) {
                    queue.addAll(template);
                    fillQueue(queue, ++round);
                }
            }
            bw.write(String.valueOf(count % A));
            bw.flush();
        }
    }
    private static void fillQueue(Queue<Integer> queue, int round) {
        for (int i = 0; i < round; i++) {
            queue.add(0);
        }
        for (int i = 0; i < round; i++) {
            queue.add(1);
        }
    }
}