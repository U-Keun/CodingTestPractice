import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, r, g, b, min;
    static int[][] expenses;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        expenses = new int[N][3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            expenses[0][i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            expenses[i][0] = r + Math.min(expenses[i - 1][1], expenses[i - 1][2]);
            expenses[i][1] = g + Math.min(expenses[i - 1][0], expenses[i - 1][2]);
            expenses[i][2] = b + Math.min(expenses[i - 1][0], expenses[i - 1][1]);
        }
        min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (expenses[N - 1][i] < min) min = expenses[N - 1][i];
        }
        System.out.println(min);
    }
}