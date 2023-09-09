import java.io.IOException;
import java.util.*;

public class Main {
    static StringBuilder print = new StringBuilder();
    static List<List<Integer>> graph = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        int[] indegree = new int[N + 1];
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            int a = readInt(), b = readInt();
            graph.get(a - 1).add(b);
            indegree[b]++;
        }
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            for (Integer i:graph.get(tmp - 1)) {
                indegree[i]--;
                if (indegree[i] == 0) queue.add(i);
            }
            print.append(tmp).append(" ");
        }
        System.out.println(print);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag)
            c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag)
            return -val;
        return val;
    }
}