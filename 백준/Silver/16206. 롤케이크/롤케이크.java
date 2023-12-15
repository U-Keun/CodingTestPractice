import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    private static class Cake implements Comparable<Cake> {
        int cakes;
        int cost;
        Cake(int cakes, int cost) {
            this.cakes = cakes;
            this.cost = cost;
        }
        private boolean isEfficient() {
            return cakes != cost;
        }
        @Override
        public int compareTo(Cake other) {
            if (this.isEfficient() && other.isEfficient()) {
                return this.cost - other.cost;
            } else if (this.isEfficient()) {
                return -1;
            } else if (other.isEfficient()) {
                return 1;
            }
            return this.cakes - other.cakes;
        }
    }
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        PriorityQueue<Cake> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int tmp = readInt();
            if (tmp % 10 == 0) {
                priorityQueue.add(new Cake(tmp / 10, Math.max(0, tmp / 10 - 1)));
            } else {
                priorityQueue.add(new Cake(tmp / 10, tmp / 10));
            }
        }
        int answer = 0;
        while (M > 0 && !priorityQueue.isEmpty()) {
            Cake tmp = priorityQueue.poll();
            if (M >= tmp.cost) {
                answer += tmp.cakes;
                M -= tmp.cost;
            } else {
                answer += M;
                M = 0;
            }
        }
        System.out.println(answer);
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