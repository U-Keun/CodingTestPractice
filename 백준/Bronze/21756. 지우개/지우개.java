import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }
        while (queue.size() != 1) {
            act(queue);
        }
        System.out.println(queue.poll());
    }
    private static void act(Queue<Integer> queue) {
        int idx = 0, l = queue.size();
        for (int i = 0; i < l; i++) {
            idx++;
            if (idx % 2 == 1) {
                queue.poll();
                continue;
            }
            queue.add(queue.poll());
        }
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
}