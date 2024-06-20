import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        int N = Integer.parseInt(st.nextToken()),
            K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100001];
        visited[N] = true;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(N);
        int time = 0;
        while (!queue.isEmpty()) {
            if (visited[K]) break;

            int l = queue.size();
            for (int i = 0; i < l; i++) {
                int tmp = queue.pollFirst();
                if (tmp > 0 && !visited[tmp - 1]) {
                    visited[tmp - 1] = true;
                    queue.addLast(tmp - 1);
                }

                if (tmp < 100000 && !visited[tmp + 1]) {
                    visited[tmp + 1] = true;
                    queue.addLast(tmp + 1);
                }

                if (tmp * 2 <= 100000 && !visited[tmp * 2]) {
                    visited[tmp * 2] = true;
                    queue.addLast(tmp * 2);
                }
            }

            time++;
        }

        System.out.println(time);
    }
}