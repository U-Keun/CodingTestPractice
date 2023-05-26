import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int N, M, k;
    static Map<Integer, Integer> numbers;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            k = Integer.parseInt(st.nextToken());
            if (numbers.containsKey(k)) {
                numbers.compute(k, (k, v) -> v + 1);
            } else numbers.put(k, 1);
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            print.append(numbers.getOrDefault(Integer.parseInt(st.nextToken()), 0)).append(" ");
        }
        System.out.println(print);
    }
}