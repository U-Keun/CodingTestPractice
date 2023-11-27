import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    private static class Tree implements Comparable<Tree> {
        int height, growthRate;
        Tree(int height, int growthRate) {
            this.height = height;
            this.growthRate = growthRate;
        }
        @Override
        public int compareTo(Tree other) {
            return this.growthRate - other.growthRate;
        }
    }
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = readInt();
        }
        PriorityQueue<Tree> trees = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            trees.add(new Tree(heights[i], readInt()));
        }
        long answer = 0;
        for (int i = 0; i < N; i++) {
            Tree tmp = trees.poll();
            answer += tmp.height + (long) tmp.growthRate * i;
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