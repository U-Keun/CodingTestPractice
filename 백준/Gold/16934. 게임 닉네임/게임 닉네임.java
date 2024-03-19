import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String input;
        char[] chars;
        Node root = new Node();
        StringBuilder print = new StringBuilder();
        Map<String, Integer> counts = new HashMap<>();
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            counts.put(input, counts.getOrDefault(input, 0) + 1);
            chars = input.toCharArray();
            Node current = root;
            int l = chars.length;
            boolean isRegistered = false;
            for (int j = 0; j < l; j++) {
                if (!isRegistered && current.getChild(chars[j]) == null) {
                    print.append(input, 0, j + 1);
                    isRegistered = true;
                }
                current.insert(chars[j]);
                current = current.getChild(chars[j]);
            }
            if (!isRegistered) {
                print.append(input);
                if (counts.get(input) > 1) print.append(counts.get(input));
            }
            print.append("\n");
        }
        System.out.println(print);
    }

    private static class Node {
        Map<Character, Node> children;
        Node() {
            children = new HashMap<>();
        }
        void insert(Character number) {
            children.putIfAbsent(number, new Node());
        }
        Node getChild(Character c) {
            return children.get(c);
        }
    }
}