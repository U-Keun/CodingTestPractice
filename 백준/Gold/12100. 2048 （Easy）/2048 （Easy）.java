import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backtracking(board, 5);
        System.out.println(max);
    }
    private static void backtracking(int[][] board, int number) {
        if (number == 0) {
            updateMax(board);
            return;
        }
        backtracking(moveLeft(board), number - 1);
        backtracking(moveUp(board), number - 1);
        backtracking(moveRight(board), number - 1);
        backtracking(moveDown(board), number - 1);
    }
    private static void updateMax(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(board[i][j], max);
            }
        }
    }
    private static int[][] moveLeft(int[][] board) {
        return move(board);
    }
    private static int[][] moveDown(int[][] board) {
        int[][] moved = move(rotate(board, 1));
        return rotate(moved, 3);
    }
    private static int[][] moveRight(int[][] board) {
        int[][] moved = move(rotate(board, 2));
        return rotate(moved, 2);
    }
    private static int[][] moveUp(int[][] board) {
        int[][] moved = move(rotate(board, 3));
        return rotate(moved, 1);
    }
    private static class CombinedPositions {
        int row, col;

        public CombinedPositions(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CombinedPositions that = (CombinedPositions) o;
            return row == that.row && col == that.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
    private static int[][] move(int[][] board) {
        int[][] newBoard = new int[N][N];
        Map<CombinedPositions, Boolean> combinedPositions = new HashMap<>();
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, N);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (newBoard[i][j] == 0) continue;
                int index = j - 1;
                while (index > 0 && newBoard[i][index] == 0) index--;
                if (!combinedPositions.getOrDefault(new CombinedPositions(i, index), false) && newBoard[i][j] == newBoard[i][index]) {
                    newBoard[i][index] += newBoard[i][j];
                    newBoard[i][j] = 0;
                    combinedPositions.put(new CombinedPositions(i, index), true);
                } else {
                    if (newBoard[i][index] == 0) {
                        newBoard[i][index] = newBoard[i][j];
                        newBoard[i][j] = 0;
                    } else if (index + 1 != j) {
                        newBoard[i][index + 1] = newBoard[i][j];
                        newBoard[i][j] = 0;
                    }
                }
            }
        }
        return newBoard;
    }
    private static int[][] rotate(int[][] board, int number) {
        if (number % 4 == 0) return board;
        int[][] rotated = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                rotated[i][j] = board[N - j - 1][i];
            }
        }
        return rotate(rotated, number % 4 - 1);
    }
}