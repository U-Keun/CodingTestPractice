import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    private static class Jewel implements Comparable<Jewel> {
        int weight, cost;
        Jewel(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
        @Override
        public int compareTo(Jewel other) {
            return this.weight - other.weight;
        }
        @Override
        public String toString() {
            return "weight : " + weight + " cost : " + cost;
        }
    }
    private static List<Integer> bags = new ArrayList<>();
    private static PriorityQueue<Jewel> jewels = new PriorityQueue<>();
    private static PriorityQueue<Integer> costs = new PriorityQueue<>(Collections.reverseOrder());
    private static long total = 0;
    public static void main(String[] args) throws IOException {
        int N = readInt(), K = readInt();
        for (int i = 0; i < N; i++) {
            jewels.add(new Jewel(readInt(), readInt()));
        }
        for (int i = 0; i < K; i++) {
            bags.add(readInt());
        }
        Collections.sort(bags);
        for (int weight : bags) {
            while (!jewels.isEmpty() && jewels.peek().weight <= weight) {
                costs.add(jewels.poll().cost);
            }
            if (!costs.isEmpty()) {
                total += costs.poll();
            }
        }
        System.out.println(total);
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