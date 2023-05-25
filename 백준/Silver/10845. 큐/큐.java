import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        Queue myqueue = new Queue();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push":
                    myqueue.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    print.append(myqueue.pop()).append('\n');
                    break;
                case "size":
                    print.append(myqueue.size()).append('\n');
                    break;
                case "empty":
                    print.append(myqueue.empty()).append('\n');
                    break;
                case "front":
                    print.append(myqueue.front()).append('\n');
                    break;
                case "back":
                    print.append(myqueue.back()).append('\n');
                    break;
            }
        }
        System.out.println(print);
    }
}
class Queue {
    private ArrayList<Integer> queue = new ArrayList<>();

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        if (queue.isEmpty()) return -1;
        int output = queue.get(0);
        queue.remove(0);
        return output;
    }

    public int size() {
        return queue.size();
    }

    public int empty() {
        if (queue.size() == 0) return 1;
        return 0;
    }

    public int front() {
        if (queue.isEmpty()) return -1;
        return queue.get(0);
    }

    public int back() {
        if (queue.isEmpty()) return -1;
        return queue.get(queue.size() - 1);
    }
}