import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        StringBuilder print = new StringBuilder();
        for (int j = 0; j < N; j++) {
            int number = Integer.parseInt(br.readLine());
            if (maxHeap.isEmpty()) {
                maxHeap.offer(number);
                print.append(maxHeap.peek()).append("\n");
                continue;
            } else if (minHeap.isEmpty()) {
                if (number < maxHeap.peek()) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(number);
                } else minHeap.offer(number);
                print.append(maxHeap.peek()).append("\n");
                continue;
            }
            int maxHeapBound = maxHeap.peek();
            int minHeapBound = minHeap.peek();
            if (number >= maxHeapBound && number <= minHeapBound) {
                if (maxHeap.size() > minHeap.size()) minHeap.offer(number);
                else maxHeap.offer(number);
            } else if (number < maxHeapBound) {
                if (maxHeap.size() > minHeap.size()) {
                    maxHeap.poll();
                    minHeap.offer(maxHeapBound);
                }
                maxHeap.offer(number);
            } else {
                if (maxHeap.size() <= minHeap.size()) {
                    minHeap.poll();
                    maxHeap.offer(minHeapBound);
                }
                minHeap.offer(number);
            }
            print.append(maxHeap.peek()).append("\n");
        }
        br.close();
        System.out.println(print);
    }
}