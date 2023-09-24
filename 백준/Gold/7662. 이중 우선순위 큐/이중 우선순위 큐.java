import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < T; i++) {
                PriorityQueue<Integer> minHeap = new PriorityQueue<>();
                PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
                HashMap<Integer, Integer> count = new HashMap<>();
                int k = Integer.parseInt(br.readLine());
                StringTokenizer st;
                Loop: for (int j = 0; j < k; j++) {
                    st = new StringTokenizer(br.readLine());
                    Integer a;
                    switch (st.nextToken()) {
                        case "I":
                            a = Integer.parseInt(st.nextToken());
                            minHeap.add(a);
                            maxHeap.add(a);
                            count.put(a, count.getOrDefault(a, 0) + 1);
                            break;
                        case "D":
                            a = Integer.parseInt(st.nextToken());
                            int value;
                            if (a == 1) {
                                do {
                                    if (maxHeap.isEmpty()) continue Loop;
                                    value = maxHeap.poll();
                                } while (count.get(value) == 0);
                            } else {
                                do {
                                    if (minHeap.isEmpty()) continue Loop;
                                    value = minHeap.poll();
                                } while (count.get(value) == 0);
                            }
                            count.put(value, count.get(value) - 1);
                            break;
                    }
                }
                int min = 0, max = 0;
                boolean isEmpty = false;
                do {
                    if (minHeap.isEmpty()) {
                        isEmpty = true;
                        break;
                    }
                    min = minHeap.poll();
                } while (count.get(min) == 0);
                do {
                    if (maxHeap.isEmpty()) {
                        isEmpty = true;
                        break;
                    }
                    max = maxHeap.poll();
                } while (count.get(max) == 0);
                if (isEmpty) print.append("EMPTY");
                else print.append(max).append(" ").append(min);
                print.append('\n');
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
}