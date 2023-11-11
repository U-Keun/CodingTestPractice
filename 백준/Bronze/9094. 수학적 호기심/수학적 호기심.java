import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            StringTokenizer st;
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < T; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
                print.append(count(n, m)).append('\n');
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
    private static int count(int n, int m) {
        int count = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int num = i * i + j * j + m;
                int denom = i * j;
                if (num % denom == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}