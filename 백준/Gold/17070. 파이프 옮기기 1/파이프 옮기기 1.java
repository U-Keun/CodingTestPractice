import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, answer = 0;
    static int[][] house;

    public static void main(String[] args) throws IOException {
        setInput();
        search(new int[]{0, 0}, new int[]{0, 1});
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
    static void search(int[] init, int[] term) {
        if (init[0] == term[0]) { // 가로로 놓여 있는 상태
            if (term[1] == N - 1) {
                if (term[0] == N - 1) answer++;
                return;
            }
            if (term[1] + 1 < N && house[term[0]][term[1] + 1] == 0) { // 오른쪽으로 회전하지 않고 이동
                search(term, new int[]{term[0], term[1] + 1});
            }
            if (term[0] + 1 < N
                && house[term[0] + 1][term[1]] == 0
                && house[term[0]][term[1] + 1] == 0
                && house[term[0] + 1][term[1] + 1] == 0) { // 오른쪽 아래로 회전하며 이동
                search(term, new int[]{term[0] + 1, term[1] + 1});
            }
        } else if (init[1] == term[1]) { // 세로로 놓여 있는 상태
            if (term[0] == N - 1) {
                if (term[1] == N - 1) answer++;
                return;
            }
            if (term[0] + 1 < N && house[term[0] + 1][term[1]] == 0) { // 아래로 회전하지 않고 이동
                search(term, new int[]{term[0] + 1, term[1]});
            }
            if (term[1] + 1 < N
                && house[term[0] + 1][term[1]] == 0
                && house[term[0]][term[1] + 1] == 0
                && house[term[0] + 1][term[1] + 1] == 0) { // 오른쪽 아래로 회전하며 이동
                search(term, new int[]{term[0] + 1, term[1] + 1});
            }
        } else { // 대각선으로 놓여 있는 상태
            if (term[0] == N - 1 && term[1] == N - 1) {
                answer++;
                return;
            }
            if (term[1] + 1 < N && house[term[0]][term[1] + 1] == 0) { // 파이프가 가로로 놓이도록 오른쪽으로 이동
                search(term, new int[]{term[0], term[1] + 1});
            }
            if (term[0] + 1 < N && house[term[0] + 1][term[1]] == 0) { // 파이프가 세로로 놓이도록 아래로 이동
                search(term, new int[]{term[0] + 1, term[1]});
            }
            if (term[0] + 1 < N && term[1] + 1 < N
                && house[term[0] + 1][term[1]] == 0
                && house[term[0]][term[1] + 1] == 0
                && house[term[0] + 1][term[1] + 1] == 0) { // 파이프가 대각선으로 놓이도록 오른쪽 아래로 이동
                search(term, new int[]{term[0] + 1, term[1] + 1});
            }
        }
    }
}