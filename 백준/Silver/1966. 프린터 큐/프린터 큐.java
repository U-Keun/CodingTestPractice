import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // BOJ 1966
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int T, N, M, order, target;
    static Queue<Integer> documents;
    static PriorityQueue<Integer> priority;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        documents = new LinkedList<>();
        priority = new PriorityQueue<>(Comparator.reverseOrder());
        Loop : for (int i = 0; i < T; i++) {
            order = 1;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int k = Integer.parseInt(st.nextToken());
                if (j == M) target = k;
                documents.add(k);
                priority.add(k);
            }
            while (true) {
                if (documents.peek() == priority.peek()) {
                    if (M == 0) {
                        print.append(order).append('\n');
                        documents.clear();
                        priority.clear();
                        continue Loop;
                    }
                    documents.poll();
                    priority.poll();
                    M--;
                    N--;
                    order++;
                    continue;
                }
                documents.add(documents.poll());
                M--;
                if (M < 0) M += N;
            }
        }
        System.out.println(print);
    }
}