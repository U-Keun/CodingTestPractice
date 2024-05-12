import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        br.close();
        boolean[] mark = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            if (mark[i]) continue;
            for (int j = i; j <= N; j += i) {
                if (mark[j]) continue;
                mark[j] = true;
                K--;
                if (K == 0) {
                    System.out.println(j);
                    return;
                }
            }
        }
    }
}