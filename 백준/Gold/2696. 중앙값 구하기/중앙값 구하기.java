import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    private static class Answer {
        List<Integer> answer;
        Answer(ArrayList<Integer> list) {
            answer = list;
        }
        @Override
        public String toString() {
            int n = answer.size() / 10 + 1;
            StringBuilder print = new StringBuilder();
            print.append(String.format("%d\n", answer.size()));
            for (int i = 0; i < n; i++) {
                int last = (i == n - 1) ? answer.size() % 10 : 10;
                for (int j = 0; j < last; j++) {
                    print.append(String.format("%d ", answer.get(10 * i + j)));
                }
                print.append("\n");
            }
            return print.toString();
        }
    }
    public static void main(String[] args) throws IOException {
        int T = readInt();
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int M = readInt();
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            ArrayList<Integer> answer = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                int number = readInt();
                if (j % 2 == 0) {
                    maxHeap.offer(number);
                    if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
                        if (minHeap.peek() < maxHeap.peek()) {
                            int tmp = minHeap.poll();
                            minHeap.offer(maxHeap.poll());
                            maxHeap.offer(tmp);
                        }
                    }
                    answer.add(maxHeap.peek());
                }
                else minHeap.offer(number);
            }
            print.append(new Answer(answer));
        }
        System.out.println(print);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
}