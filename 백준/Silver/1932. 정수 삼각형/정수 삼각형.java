import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, k, answer;
    static int[][] record;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        record = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                k = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    if (i == 0) {
                        record[i][j] = k;
                        continue;
                    }
                    record[i][j] = record[i - 1][j] + k;
                } else if (i == j) {
                    record[i][j] = record[i - 1][j - 1] + k;
                } else {
                    record[i][j] = Math.max(record[i - 1][j - 1], record[i - 1][j]) + k;
                }
            }
        }
        answer = 0;
        for (int i:record[N - 1]) {
            if (i > answer) answer = i;
        }
        System.out.println(answer);
    }
}