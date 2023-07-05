import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, T, time = 0;
    static int[][] map;
    static boolean[][][] visited;
    static Queue<Site> sites = new LinkedList<>();
    static int[] dx = {-1, 0, 0, 1}, dy = {0, 1, -1, 0};
    static boolean success;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (map[0][0] == 2) sites.add(new Site(0, 0, true));
        else sites.add(new Site(0, 0, false));
        visited[0][0][0] = true;
        Loop:
        while (!sites.isEmpty()) {
            int l = sites.size();
            for (int i = 0; i < l; i++) {
                Site site = sites.poll();
                if (site.row == N - 1 && site.col == M - 1) {
                    success = true;
                    break Loop;
                }
                if (time > T) break Loop;
                for (int j = 0; j < 4; j++) {
                    int newX = site.col + dx[j];
                    int newY = site.row + dy[j];
                    if (newX >= 0 && newX < M
                            && newY >= 0 && newY < N) {
                        if (site.hasSword && map[newY][newX] >= 0 && !visited[newY][newX][1]) {
                            sites.add(new Site(newY, newX, true));
                            visited[newY][newX][1] = true;
                        } else if (map[newY][newX] >= 0 && !visited[newY][newX][site.hasSword ? 1 : 0]) {
                            if (map[newY][newX] == 0) {
                                sites.add(new Site(newY, newX, false));
                                visited[newY][newX][0] = true;
                            } else if (map[newY][newX] == 2) {
                                sites.add(new Site(newY, newX, true));
                                visited[newY][newX][1] = true;
                            }
                        }
                    }
                }
            }
            time++;
        }
        if (success) System.out.println(time);
        else System.out.println("Fail");
    }
}

class Site {
    int row;
    int col;
    boolean hasSword;

    public Site(int row, int col, boolean hasSword) {
        this.row = row;
        this.col = col;
        this.hasSword = hasSword;
    }
}