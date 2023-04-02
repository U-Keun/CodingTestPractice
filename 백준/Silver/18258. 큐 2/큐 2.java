import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        StringTokenizer order;
        int N = Integer.parseInt(br.readLine());
        int last = 0;
        for (int i=0; i < N; i++) {
            order = new StringTokenizer(br.readLine(), " ");
            switch (order.nextToken()) {
                case "push":
                    last = Integer.parseInt(order.nextToken());
                    q.add(last);
                    break;
                case "pop":
                    if (!q.isEmpty()) print.append(q.poll()).append('\n');
                    else print.append(-1).append('\n');
                    break;
                case "size":
                    print.append(q.size()).append('\n');
                    break;
                case "empty":
                    if (q.isEmpty()) print.append(1).append('\n');
                    else print.append(0).append('\n');
                    break;
                case "front":
                    if (!q.isEmpty()) print.append(q.peek()).append('\n');
                    else print.append(-1).append('\n');
                    break;
                case "back":
                    if (!q.isEmpty()) print.append(last).append('\n');
                    else print.append(-1).append('\n');
                    break;
            }
        }
        System.out.println(print);
    }
}