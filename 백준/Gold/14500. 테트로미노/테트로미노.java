import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, max = Integer.MIN_VALUE;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        setVariables();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j + 3 < M) {
                    max = Math.max(max, board[i][j] + board[i][j + 1] + board[i][j + 2] + board[i][j + 3]);
                }
                if (i + 3 < N) {
                    max = Math.max(max, board[i][j] + board[i + 1][j] + board[i + 2][j] + board[i + 3][j]);
                }
                if (i + 1 < N && j + 1 < M) {
                    max = Math.max(max, board[i][j] + board[i + 1][j] + board[i][j + 1] + board[i + 1][j + 1]);
                }
                if (i + 1 < N && j + 2 < M) {
                    max = Math.max(max, twoByThree(i, j));
                }
                if (i + 2 < N && j + 1 < M) {
                    max = Math.max(max, threeByTwo(i, j));
                }
            }
        }
        System.out.println(max);
    }
    static void setVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    static int twoByThree(int i, int j) {
        int answer = Integer.MIN_VALUE;
        int box = 0;
        for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 3; l++) {
                box += board[i + k][j + l];
            }
        }
        int[][] rowIndices = {{0, 0}, {0, 0}, {0, 0}, {0, 1}, {1, 0}, {1, 1}, {1, 1}, {1, 1}},
                colIndices = {{0, 1}, {0, 2}, {1, 2}, {0, 2}, {0, 2}, {0, 1}, {0, 2}, {1, 2}};
        for (int k = 0; k < 8; k++) {
            answer = Math.max(answer, box - board[i + rowIndices[k][0]][j + colIndices[k][0]]
                                - board[i + rowIndices[k][1]][j + colIndices[k][1]]);
        }
        return answer;
    }
    static int threeByTwo(int i, int j) {
        int answer = Integer.MIN_VALUE;
        int box = 0;
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 2; l++) {
                box += board[i + k][j + l];
            }
        }
        int[][] rowIndices = {{0, 1}, {0, 2}, {1, 2}, {0, 2}, {0, 2}, {0, 1}, {0, 2}, {1, 2}},
                colIndices = {{0, 0}, {0, 0}, {0, 0}, {0, 1}, {1, 0}, {1, 1}, {1, 1}, {1, 1}};
        for (int k = 0; k < 8; k++) {
            answer = Math.max(answer, box - board[i + rowIndices[k][0]][j + colIndices[k][0]]
                    - board[i + rowIndices[k][1]][j + colIndices[k][1]]);
        }
        return answer;
    }
}