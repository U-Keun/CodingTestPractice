import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), k = Math.min(a, b - a);
            long den = 1, answer = 1;
            for (int j = 0; j < k; j++) {
                answer *= b - j;
                den *= k - j;
            }
            answer /= den;
            print.append(answer).append('\n');
        }
        br.close();
        bw.write(print.toString());
        bw.flush();
        bw.close();
    }
}