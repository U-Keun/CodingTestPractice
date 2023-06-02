import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, H, tomato, unripe, tries, answer;
    static int[][][] bascket;
    static Queue<int[]> orders;
    static int[] x, y, z, r;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        unripe = 0;
        bascket = new int[H][M][N];
        orders = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    tomato = Integer.parseInt(st.nextToken());
                    if (tomato == 1) {
                        orders.add(new int[]{i, j, k});
                    } else if (tomato == 0) unripe++;
                    bascket[i][j][k] = tomato;
                }
            }
        }
        answer = -1;
        x = new int[]{-1, 0, 0, 1, 0, 0};
        y = new int[]{0, 1, -1, 0, 0, 0};
        z = new int[]{0, 0, 0, 0, 1, -1};
        while (!orders.isEmpty()) {
            tries = orders.size();
            for (int i = 0; i < tries; i++) {
                r = orders.poll();
                for (int j = 0; j < 6; j++) {
                    if (r[0] + z[j] >= 0 && r[0] + z[j] < H
                            && r[1] + y[j] >= 0 && r[1] + y[j] < M
                            && r[2] + x[j] >= 0 && r[2] + x[j] < N
                            && bascket[r[0] + z[j]][r[1] + y[j]][r[2] + x[j]] == 0) {
                        bascket[r[0] + z[j]][r[1] + y[j]][r[2] + x[j]]++;
                        unripe--;
                        orders.add(new int[]{r[0] + z[j], r[1] + y[j], r[2] + x[j]});
                    }
                }
            }
            answer++;
        }
        if (unripe > 0) System.out.println(-1);
        else System.out.println(answer);
    }
}