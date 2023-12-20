import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int K = readInt();
        if (N == K) {
            System.out.println(0);
            System.out.println(1);
            return;
        }
        int[] visited = new int[100001];
        visited[N] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        int time = 0;
        Loop : while (!queue.isEmpty()) {
            int l = queue.size();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < l; i++) {
                int tmp = queue.poll();
                if (tmp - 1 >= 0 && visited[tmp - 1] == 0) {
                    map.put(tmp - 1, map.getOrDefault(tmp - 1, 0) + visited[tmp]);
                }
                if (tmp + 1 <= 100000 && visited[tmp + 1] == 0) {
                    map.put(tmp + 1, map.getOrDefault(tmp + 1, 0) + visited[tmp]);
                }
                if (2 * tmp <= 100000 && visited[2 * tmp] == 0) {
                    map.put(2 * tmp, map.getOrDefault(2 * tmp, 0) + visited[tmp]);
                }
            }
            time++;
            for (Integer key : map.keySet()) {
                visited[key] = map.get(key);
                queue.add(key);
                if (key == K) break Loop;
            }

        }
        System.out.println(time);
        System.out.println(visited[K]);
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