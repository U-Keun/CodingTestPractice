import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class MyStack {
    ArrayList<Integer> MyStack = new ArrayList<>();
    void push(int x) {
        this.MyStack.add(x);
    }
    int pop() {
        if (!this.MyStack.isEmpty()) {
            int p = this.MyStack.get(this.MyStack.size() - 1);
            this.MyStack.remove(this.MyStack.size() - 1);
            return p;
        } else return -1;
    }
    int size() {
        return this.MyStack.size();
    }
    int empty() {
        if (this.MyStack.isEmpty()) return 1;
        else return 0;
    }
    int top() {
        if(this.MyStack.isEmpty()) return -1;
        else return this.MyStack.get(this.MyStack.size()-1);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        MyStack stack = new MyStack();
        for (int i =0 ; i < N; i++) {
            String[] order = br.readLine().split(" ");
            switch (order[0]){
                case "push":
                    stack.push(Integer.parseInt(order[1]));
                    break;
                case "pop":
                    print.append(stack.pop()).append('\n');
                    break;
                case "size":
                    print.append(stack.size()).append('\n');
                    break;
                case "empty":
                    print.append(stack.empty()).append('\n');
                    break;
                case "top":
                    print.append(stack.top()).append('\n');
                    break;
            }
        }
        System.out.println(print);
    }
}