import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        loop : for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];
            for (int j = 0; j < n; j++) {
                numbers[j] = br.readLine();
            }
            Arrays.sort(numbers);
            Node root = new Node();
            for (int j = 0; j < n; j++) {
                Node current = root;
                for (char c : numbers[j].toCharArray()) {
                    if (current.isRegistered) {
                        print.append("NO\n");
                        continue loop;
                    }
                    current.insert(c);
                    current = current.getChild(c);
                }
                current.setRegistered();
            }
            print.append("YES\n");
        }
        br.close();
        System.out.println(print);
    }

    private static class Node {
        Map<Character, Node> children;
        boolean isRegistered;
        Node() {
            children = new HashMap<>();
            isRegistered = false;
        }
        void insert(Character number) {
            children.putIfAbsent(number, new Node());
        }
        Node getChild(Character number) {
            return children.get(number);
        }
        void setRegistered() {
            this.isRegistered = true;
        }
    }
}