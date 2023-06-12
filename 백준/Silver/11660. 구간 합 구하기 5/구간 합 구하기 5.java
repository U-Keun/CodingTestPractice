import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int N, M, x1, y1, x2, y2, value;
    static int[][] record;
    static int[] x = {-1, 0}, y = {0, -1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        record = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                value = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 2; k++) {
                    if (i + y[k] >= 0 && i + y[k] < N
                            && j + x[k] >= 0 && j + x[k] < N) {
                        value += record[i + y[k]][j + x[k]];
                    }
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    value -= record[i - 1][j - 1];
                }
                record[i][j] = value;
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            value = record[x2 - 1][y2 - 1];
            if (x1 - 2 >= 0) value -= record[x1 - 2][y2 - 1];
            if (y1 - 2 >= 0) value -= record[x2 - 1][y1 - 2];
            if (y1 - 2 >= 0 && x1 - 2 >= 0) value += record[x1 - 2][y1 - 2];
            print.append(value).append('\n');
        }
        System.out.println(print);
    }
}