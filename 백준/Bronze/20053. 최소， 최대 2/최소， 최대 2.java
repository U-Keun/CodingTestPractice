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
            for (int i = 0; i < T; i++) {
                int N = Integer.parseInt(br.readLine());
                StringTokenizer st = new StringTokenizer(br.readLine());
                int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
                while (N-- > 0) {
                    int tmp = Integer.parseInt(st.nextToken());
                    max = Math.max(max, tmp);
                    min = Math.min(min, tmp);
                }
                bw.write(String.format("%d %d\n", min, max));
            }
            bw.flush();
        }
    }
}