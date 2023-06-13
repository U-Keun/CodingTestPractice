import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static int t, n, answer;
    static Map<String, Integer> clothes = new HashMap<>();

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            answer = 1;
            for (int j = 0; j < n; j++) {
                String kind = br.readLine().split(" ")[1];
                clothes.compute(kind, (k, v) -> (v == null) ? 1 : v + 1);
            }
            for (int j:clothes.values()) {
                answer *= j + 1;
            }
            print.append(answer - 1).append('\n');
            clothes.clear();
        }
        System.out.println(print);
    }
}