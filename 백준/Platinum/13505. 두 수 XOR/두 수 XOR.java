import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static class Node {
        Node[] children;
        Node() {
            children = new Node[2];
        }
    }
    private static Node root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        root = new Node();
        for (int number : numbers) {
            insert(root, number);
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(XORWith(numbers[i]), answer);
        }

        System.out.println(answer);
    }
    private static void insert(Node current, int number) {
        for (int i = 30; i >= 0; i--) {
            int index = ((number & (1 << i)) == 0) ? 0 : 1;
            if (current.children[index] == null) current.children[index] = new Node();
            current = current.children[index];
        }
    }
    private static int XORWith(int number) {
        int result = 0;
        Node current = root;
        for (int i = 30; i >= 0; i--) {
            int index = ((number & (1 << i)) == 0) ? 1 : 0;
            if (current.children[index] == null) {
                index ^= 1;
            } else result += 1 << i;
            current = current.children[index];
        }
        return result;
    }
}