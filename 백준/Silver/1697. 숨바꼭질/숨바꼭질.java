import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K, time = 0, l, m;
    static Queue<Integer> location = new LinkedList<>();
    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        location.add(N);
        Loop : while (true) {
            l = location.size();
            for (int i = 0; i < l; i++) {
                m = location.poll();
                visited[m] = true;
                if (m == K) break Loop;
                if (m - 1 >= 0 && !visited[m - 1]) location.add(m - 1);
                if (m + 1 <= 100000 && !visited[m + 1]) location.add(m + 1);
                if (2 * m <= 100000 && !visited[2 * m]) location.add(2 * m);
            }
            time++;
        }
        System.out.println(time);
    }
}