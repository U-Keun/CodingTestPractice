import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int A, K, l, value, answer;
    static Queue<Integer> record;
    static boolean[] visited = new boolean[1000001];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        record = new LinkedList<>();
        record.add(A);
        answer = 0;
        Loop : while (!record.isEmpty()) {
            l = record.size();
            answer++;
            for (int i = 0; i < l; i++) {
                value = record.poll();
                visited[value] = true;
                if (value + 1 <= K && !visited[value + 1]) {
                    if (value + 1 == K) break Loop;
                    record.add(value + 1);
                }
                if (value * 2 <= K && !visited[value * 2]) {
                    if (value * 2 == K) break Loop;
                    record.add(value * 2);
                }
            }
        }
        System.out.println(answer);
    }
}