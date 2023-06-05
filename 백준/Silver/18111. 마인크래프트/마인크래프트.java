import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // BOJ 18111
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, B, h, plus, minus, timeTaken;
    static int[][] ground;
    static int[] answer = new int[2];
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        ground = new int[N][M];
        h = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                ground[i][j] =Integer.parseInt(st.nextToken());
                h += ground[i][j];
            }
        }
        h = Math.round((float) h / (N * M));
        do {
            plus = 0;
            minus = 0;
            calc(h);
            if (B + plus < minus) break;
            timeTaken = 2 * plus + minus;
            if (answer[0] == 0 || answer[0] >= timeTaken) {
                answer[0] = timeTaken;
                answer[1] = h;
            }
            h++;
        } while (B + plus >= minus);
        if (answer[0] == 0) {
            plus = 0;
            minus = 0;
            calc(h - 1);
            answer[0] = 2 * plus + minus;
            answer[1] = h - 1;
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
    static void calc(int h) {
        int k;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                k = ground[i][j] - h;
                if (k > 0) plus += k;
                else minus -= k;
            }
        }
    }
}