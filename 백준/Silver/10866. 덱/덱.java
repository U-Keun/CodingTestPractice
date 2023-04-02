import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class MyDequeue {
    ArrayList<Integer> mydequeue;
    public MyDequeue() {
        this.mydequeue = new ArrayList<Integer>();
    }
    void push_front(int x) {
        this.mydequeue.add(0, x);
    }
    void push_back(int x) {
        this.mydequeue.add(x);
    }
    int pop_front() {
        int pf;
        if (this.mydequeue.isEmpty()) return -1;
        else {
            pf = this.mydequeue.get(0);
            this.mydequeue.remove(0);
            return pf;
        }
    }
    int pop_back() {
        int pb;
        if (this.mydequeue.isEmpty()) return -1;
        else {
            pb = this.mydequeue.get(this.mydequeue.size()-1);
            this.mydequeue.remove(this.mydequeue.size()-1);
            return pb;
        }
    }
    int size() {
        return this.mydequeue.size();
    }
    int empty() {
        if(this.mydequeue.isEmpty()) return 1;
        else return 0;
    }
    int front() {
        if (this.mydequeue.isEmpty()) return -1;
        else return this.mydequeue.get(0);
    }
    int back() {
        if (this.mydequeue.isEmpty()) return -1;
        else return this.mydequeue.get(this.mydequeue.size()-1);
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        MyDequeue dequeue = new MyDequeue();
        StringTokenizer order;
        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            order = new StringTokenizer(br.readLine()," ");
            switch (order.nextToken()) {
                case "push_front":
                    dequeue.push_front(Integer.parseInt(order.nextToken()));
                    break;
                case "push_back":
                    dequeue.push_back(Integer.parseInt(order.nextToken()));
                    break;
                case "pop_front":
                    int a = dequeue.pop_front();
                    print.append(a).append('\n');
                    break;
                case "pop_back":
                    int b = dequeue.pop_back();
                    print.append(b).append('\n');
                    break;
                case "size":
                    print.append(dequeue.size()).append('\n');
                    break;
                case "empty":
                    print.append(dequeue.empty()).append('\n');
                    break;
                case "front":
                    print.append(dequeue.front()).append('\n');
                    break;
                case "back":
                    print.append(dequeue.back()).append('\n');
                    break;
            }
        }
        System.out.println(print);
    }
}