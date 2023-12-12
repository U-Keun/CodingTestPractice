import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();
        char prev = input[0];
        int count = 1;
        List<Integer> counts = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            if (prev == input[i]) {
                count++;
            } else {
                counts.add(count);
                count = 1;
                prev = input[i];
            }
        }
        counts.add(count);
        int l = counts.size();
        int a = 0, b = 0;
        for (int i = 0; i < l - 1; i++) {
            if (i % 2 == 0) {
                a += counts.get(i);
            } else b += counts.get(i);
        }
        int left = Math.min(a, b);
        a = 0;
        b = 0;
        for (int i = 1; i < l; i++) {
            if (i % 2 == 0) {
                a += counts.get(i);
            } else b += counts.get(i);
        }
        int right = Math.min(a, b);
        System.out.println(Math.min(left, right));
    }
}