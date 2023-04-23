import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        char[][] printBoard = new char[N][N];
        String[] row;
        for (int i = 0; i < N; i++) {
            row = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                board[i][j] = row[j].equals(".") ? 0 : 1;
            }
        }
        boolean failed = false;
        for (int i = 0; i < N; i++) {
            row = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                if (row[j].equals("x")) {
                    if (board[i][j] == 0) {
                        printBoard[i][j] = (char)(mines(board, i, j) + '0');
                    } else {
                        failed = true;
                    }
                } else {
                    printBoard[i][j] = '.';
                }
            }
        }
        if (failed) markMines(board, printBoard);
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                print.append(printBoard[i][j]);
            }
            print.append('\n');
        }
        System.out.println(print);
    }
    public static int mines(int[][] board, int i, int j) {
        int count = 0;
        int n = board.length;
        int[] x = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] y = {1, 0, -1, 1, -1, 1, 0, -1};
        for (int k = 0; k < 8; k++) {
            if (i + x[k] < n && j + y[k] < n && i + x[k] >= 0 && j + y[k] >= 0 ) {
                if (board[i + x[k]][j + y[k]] == 1) count++;
            }
        }
        return count;
    }
    public static void markMines(int[][] board, char[][] printBoard) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) printBoard[i][j] = '*';
            }
        }
    }
}