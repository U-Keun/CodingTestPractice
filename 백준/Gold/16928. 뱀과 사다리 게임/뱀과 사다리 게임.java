import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
            int[] board = new int[101];
            for (int i = 1; i <= 100; i++) board[i] = i;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
                board[x] = y;
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
                board[u] = v;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.add(1);
            int answer = 0;
            Loop : while (!queue.isEmpty()) {
                answer++;
                int l = queue.size();
                for (int i = 0; i < l; i++) {
                    int tmp = queue.poll();
                    for (int j = 1; j <= 6; j++) {
                        if (board[tmp + j] < 0) continue;
                        if (board[tmp + j] == 100) break Loop;
                        queue.add(board[tmp + j]);
                        board[tmp + j] = -1;
                    }
                }
            }
            bw.write(String.valueOf(answer));
            bw.flush();
        }
    }
}