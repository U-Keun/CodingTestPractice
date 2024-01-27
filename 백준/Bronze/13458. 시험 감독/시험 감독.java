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
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] candidates = new int[N];
            for (int i = 0; i < N; i++) {
                candidates[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            int B = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
            long answer = N;
            for (int i = 0; i < N; i++) {
                candidates[i] = Math.max(0, candidates[i] - B);
                answer += (candidates[i] + C - 1) / C;
            }
            bw.write(String.valueOf(answer));
            bw.flush();
        }
    }
}