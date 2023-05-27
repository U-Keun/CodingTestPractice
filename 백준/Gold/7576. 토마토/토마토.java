import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, tomato, unripe, tries, answer;
    static int[][] bascket;
    static Queue<int[]> orders;
    static int[] x, y, r;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        unripe = 0;
        bascket = new int[M][N];
        orders = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                tomato = Integer.parseInt(st.nextToken());
                if (tomato == 1) {
                    orders.add(new int[]{i, j});
                } else if (tomato == 0) unripe++;
                bascket[i][j] = tomato;
            }
        }
        answer = -1;
        x = new int[]{-1, 0, 0, 1};
        y = new int[]{0, 1, -1, 0};
        while (!orders.isEmpty()) {
            tries = orders.size();
            for (int i = 0; i < tries; i++) {
                r = orders.poll();
                for (int j = 0; j < 4; j++) {
                    if (r[0] + y[j] >= 0 && r[0] + y[j] < M
                            && r[1] + x[j] >= 0 && r[1] + x[j] < N
                            && bascket[r[0] + y[j]][r[1] + x[j]] == 0) {
                        bascket[r[0] + y[j]][r[1] + x[j]]++;
                        unripe--;
                        orders.add(new int[]{r[0] + y[j], r[1] + x[j]});
                    }
                }
            }
            answer++;
        }
        if (unripe > 0) System.out.println(-1);
        else System.out.println(answer);
    }
}