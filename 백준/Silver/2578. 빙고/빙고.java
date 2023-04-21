import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        int bingo = 0;
        int tries = 0;
        int input;
        int[] coord = new int[2];
        Loop1: for (int i = 0; i < 5; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                input = Integer.parseInt(st2.nextToken());
                coord = mark(board, input);

                tries++;

                if (coord[0] == coord[1]) {
                    if (checkDiag(board)) bingo++;
                }

                if (coord[0] + coord[1] == 4) {
                    if (checkRevDiag(board)) bingo++;
                }

                if (checkRow(board, coord[0])) bingo++;
                if (checkCol(board, coord[1])) bingo++;
                if (bingo >= 3) break Loop1;
            }
        }
        System.out.println(tries);
    }
    public static int[] mark(int[][] board, int n) {
        int k = board.length;
        int[] coord = new int[2];
        Loop2: for (int i = 0; i < k; i++) {
            for (int j =0; j < k; j++) {
                if (board[i][j] == n) {
                    board[i][j] = 0;
                    coord[0] = i;
                    coord[1] = j;
                    break Loop2;
                }
            }
        }
        return coord;
    }
    public static boolean checkRow(int[][] board, int row) {
        int count = 0;
        for (int j:board[row]) {
            if (j == 0) count++;
        }
        return count == board[row].length;
    }
    public static boolean checkCol(int[][] board, int col) {
        int count = 0;
        for (int j = 0; j < board[0].length; j++) {
            if (board[j][col] == 0) count++;
        }
        return count == board[0].length;
    }
    public static boolean checkDiag(int[][] board) {
        int count = 0;
        int n = board[0].length;
        for (int j = 0; j < board[0].length; j++) {
            if (board[j][j] == 0) count++;
        }
        return count == board[0].length;
    }
    public static boolean checkRevDiag(int[][] board) {
        int count = 0;
        int n = board[0].length;
        for (int j = 0; j < n; j++) {
            if (board[j][n - j - 1] == 0) count++;
        }
        return count == board[0].length;
    }
}