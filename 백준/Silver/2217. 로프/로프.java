import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> weights = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            weights.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(weights);
        int max = 0;
        int n = weights.size();
        int capacity;
        for (int i = 0; i < n; i++) {
            capacity = weights.get(i) * (n - i);
            if (capacity > max) max = capacity;
        }
        System.out.println(max);
    }
}