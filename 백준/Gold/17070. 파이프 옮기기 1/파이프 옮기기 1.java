import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, answer = 0;
    static int[][] house;

    public static void main(String[] args) throws IOException {
        setInput();
        search(0, 0, 0, 1);
        System.out.println(answer);
    }
    static void setInput() throws IOException {
        N = Integer.parseInt(br.readLine());
        house = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    static void search(int x1, int y1, int x2, int y2) {
        if (x2 == N - 1 && y2 == N - 1) {
            answer++;
            return;
        }
        if (x1 == x2) { // 가로 상태.
            if (y2 + 1 < N && house[x2][y2 + 1] == 0) {
                search(x2, y2, x2, y2 + 1);
            }
            if (x2 + 1 < N && y2 + 1 < N && house[x2 + 1][y2] == 0 && house[x2][y2 + 1] == 0 && house[x2 + 1][y2 + 1] == 0) {
                search(x2, y2, x2 + 1, y2 + 1);
            }
        } else if (y1 == y2) { // 세로 상태.
            if (x2 + 1 < N && house[x2 + 1][y2] == 0) {
                search(x2, y2, x2 + 1, y2);
            }
            if (x2 + 1 < N && y2 + 1 < N && house[x2 + 1][y2] == 0 && house[x2][y2 + 1] == 0 && house[x2 + 1][y2 + 1] == 0) {
                search(x2, y2, x2 + 1, y2 + 1);
            }
        } else { // 대각선 상태
            if (y2 + 1 < N && house[x2][y2 + 1] == 0) {
                search(x2, y2, x2, y2 + 1);
            }
            if (x2 + 1 < N && house[x2 + 1][y2] == 0) {
                search(x2, y2, x2 + 1, y2);
            }
            if (x2 + 1 < N && y2 + 1 < N && house[x2 + 1][y2] == 0 && house[x2][y2 + 1] == 0 && house[x2 + 1][y2 + 1] == 0) {
                search(x2, y2, x2 + 1, y2 + 1);
            }
        }
    }
}