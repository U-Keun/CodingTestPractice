import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            int[] sum = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
            }
            int max = Integer.MIN_VALUE, idx = 0;
            for (int i = K; i <= N; i++) {
                max = Math.max(max, sum[i] - sum[idx++]);
            }
            bw.write(String.valueOf(max));
            bw.flush();
        }
    }
}