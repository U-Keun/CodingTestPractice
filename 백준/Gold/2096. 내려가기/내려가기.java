import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, tmp, a, b, c, min, max;
    static int[][][] record;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        record = new int[N][3][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            tmp = Integer.parseInt(st.nextToken());
            record[0][i][0] = tmp;
            record[0][i][1] = tmp;
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            record[i][0][0] = Math.min(record[i - 1][0][0], record[i - 1][1][0]) + a;
            record[i][0][1] = Math.max(record[i - 1][0][1], record[i - 1][1][1]) + a;
            record[i][1][0] = Math.min(Math.min(record[i - 1][0][0], record[i - 1][1][0]), record[i - 1][2][0]) + b;
            record[i][1][1] = Math.max(Math.max(record[i - 1][0][1], record[i - 1][1][1]), record[i - 1][2][1]) + b;
            record[i][2][0] = Math.min(record[i - 1][1][0], record[i - 1][2][0]) + c;
            record[i][2][1] = Math.max(record[i - 1][1][1], record[i - 1][2][1]) + c;
        }
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            if (record[N - 1][i][0] < min) min = record[N - 1][i][0];
            if (record[N - 1][i][1] > max) max = record[N - 1][i][1];
        }
        System.out.println(max + " " + min);
    }
}