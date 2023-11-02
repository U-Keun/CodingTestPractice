import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            List<Integer> command = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = N - 1; i >= 0; i--) {
                switch (command.get(i)) {
                    case 1:
                        deque.addFirst(N - i);
                        break;
                    case 2:
                        if (!deque.isEmpty()) {
                            int tmp = deque.pollFirst();
                            deque.addFirst(N - i);
                            deque.addFirst(tmp);
                        } else deque.addFirst(N - i);
                        break;
                    case 3:
                        deque.addLast(N - i);
                        break;
                }
            }
            StringBuilder print = new StringBuilder();
            print.append(deque.pollFirst());
            while (!deque.isEmpty()) {
                print.append(" ").append(deque.pollFirst());
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
}