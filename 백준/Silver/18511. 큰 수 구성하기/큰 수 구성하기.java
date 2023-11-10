import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < K; i++) {
                set.add(Integer.parseInt(st.nextToken()));
            }
            while (!check(N, set)) N--;
            bw.write(String.valueOf(N));
            bw.flush();
        }
    }
    private static boolean check(int number, Set<Integer> set) {
        while (number > 0) {
            int k = number % 10;
            if (!set.contains(k)) return false;
            number /= 10;
        }
        return true;
    }
}