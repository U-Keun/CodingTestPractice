import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder print;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        int N = Integer.parseInt(st.nextToken()),
            K = Integer.parseInt(st.nextToken());

        int[] visited = new int[100001];
        Arrays.fill(visited, -1);
        visited[N] = N;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(N);
        int time = 0;
        while (!queue.isEmpty()) {
            if (visited[K] >= 0) break;

            int l = queue.size();
            for (int i = 0; i < l; i++) {
                int tmp = queue.pollFirst();
                if (tmp > 0 && visited[tmp - 1] < 0) {
                    visited[tmp - 1] = tmp;
                    queue.addLast(tmp - 1);
                }

                if (tmp < 100000 && visited[tmp + 1] < 0) {
                    visited[tmp + 1] = tmp;
                    queue.addLast(tmp + 1);
                }

                if (tmp * 2 <= 100000 && visited[tmp * 2] < 0) {
                    visited[tmp * 2] = tmp;
                    queue.addLast(tmp * 2);
                }
            }

            time++;
        }

        print = new StringBuilder();
        print.append(time).append("\n");

        trackingPath(visited, K, N);

        System.out.println(print);
    }

    private static void trackingPath(int[] visited, int current, int N) {
        if (current == N) {
            print.append(current).append(" ");
            return;
        }

        trackingPath(visited, visited[current], N);
        print.append(current).append(" ");
    }
}