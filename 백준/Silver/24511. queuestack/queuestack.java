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
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(st.nextToken());
            if (check[i]) stack.push(k);
        }
        while (!stack.isEmpty()) queue.add(stack.pop());
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < M; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
            bw.write(queue.poll() + " ");
        }
        bw.flush();
        bw.close();
    }

}