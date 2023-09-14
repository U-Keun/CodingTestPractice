import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] check = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (st.nextToken().equals("0")) check[i] = true;
        }
        Deque<Integer> deque = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(st.nextToken());
            if (check[i]) deque.addFirst(k);
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < M; i++) {
            deque.addLast(Integer.parseInt(st.nextToken()));
            bw.write(deque.pollFirst() + " ");
        }
        bw.flush();
        bw.close();
    }
}