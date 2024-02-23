import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static class LinkedNode {
        int number;
        LinkedNode previous;
        public LinkedNode(int number, LinkedNode previous) {
            this.number = number;
            this.previous = previous;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<LinkedNode> linkedNodeDeque = new ArrayDeque<>();
        linkedNodeDeque.addLast(new LinkedNode(1, null));
        boolean[] visited = new boolean[N + 1];
        while (linkedNodeDeque.peek().number != N) {
            LinkedNode tmp = linkedNodeDeque.pollFirst();
            if (visited[tmp.number]) continue;
            if (tmp.number + 1 <= N) {
                linkedNodeDeque.addLast(new LinkedNode(tmp.number + 1, tmp));
            }
            if (tmp.number * 2 <= N) {
                linkedNodeDeque.addLast(new LinkedNode(tmp.number * 2, tmp));
            }
            if (tmp.number * 3 <= N) {
                linkedNodeDeque.addLast(new LinkedNode(tmp.number * 3, tmp));
            }
            visited[tmp.number] = true;
        }
        LinkedNode answer = linkedNodeDeque.pollFirst();
        StringBuilder sequence = new StringBuilder();
        sequence.append(answer.number).append(" ");
        int count = 0;
        while (answer.previous != null) {
            count++;
            answer = answer.previous;
            sequence.append(answer.number).append(" ");
        }
        System.out.println(count);
        System.out.println(sequence);
    }
}