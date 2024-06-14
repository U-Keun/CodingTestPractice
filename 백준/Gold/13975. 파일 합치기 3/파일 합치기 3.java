import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            Long[] files = Arrays.stream(br.readLine().split(" "))
                    .map(Long::parseLong)
                    .toArray(Long[]::new);

            PriorityQueue<Long> pque = new PriorityQueue<>(Arrays.asList(files));
            long answer = 0;
            while (pque.size() > 1) {
                long a = pque.poll(), b = pque.poll();
                answer += a + b;
                pque.add(a + b);
            }
            print.append(answer).append("\n");
        }
        br.close();
        System.out.println(print);
    }
}