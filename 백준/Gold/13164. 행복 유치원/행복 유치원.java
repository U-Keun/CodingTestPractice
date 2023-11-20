import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i = 1; i < N; i++) {
                int current = Integer.parseInt(st.nextToken());
                priorityQueue.add(current - prev);
                prev = current;
            }
            for (int i = 1; i < K; i++) priorityQueue.poll();
            int answer = 0;
            while (!priorityQueue.isEmpty()) {
                answer += priorityQueue.poll();
            }
            bw.write(String.valueOf(answer));
            bw.flush();
        }
    }
}