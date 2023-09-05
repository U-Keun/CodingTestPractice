import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] input;
    static StringBuilder print = new StringBuilder();
    static int M;
    static int[][] board = new int[9][9];
    static int[][] checkboard = new int[3][9];
    static ArrayList<Blank> blanks = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 9; i++) {
            input = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                board[i][j] = input[j] - '0';
                if (board[i][j] == 0) {
                    blanks.add(new Blank(i, j));
                } else {
                    checkboard[0][i] |= 1 << board[i][j];
                    checkboard[1][j] |= 1 << board[i][j];
                    checkboard[2][i / 3 * 3 + j / 3] |= 1 << board[i][j];
                }
            }
        }
        M = blanks.size();
        recurrence(0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                print.append(board[i][j]);
            }
            print.append('\n');
        }
        System.out.println(print);
    }
    public static boolean recurrence(int j) {
        if (j == M) return true;
        Blank tmp = blanks.get(j);
        for (int i = 1; i <= 9; i++) {
            if ((checkboard[0][tmp.rowIdx] & 1 << i) != 0
                || (checkboard[1][tmp.colIdx] & 1 << i) != 0
                || (checkboard[2][tmp.rowIdx / 3 * 3 + tmp.colIdx / 3] & 1 << i) != 0) continue;
            checkboard[0][tmp.rowIdx] |= 1 << i;
            checkboard[1][tmp.colIdx] |= 1 << i;
            checkboard[2][tmp.rowIdx / 3 * 3 + tmp.colIdx / 3] |= 1 << i;
            board[tmp.rowIdx][tmp.colIdx] = i;
            if (recurrence(j + 1)) return true;
            checkboard[0][tmp.rowIdx] &= ~(1 << i);
            checkboard[1][tmp.colIdx] &= ~(1 << i);
            checkboard[2][tmp.rowIdx / 3 * 3 + tmp.colIdx / 3] &= ~(1 << i);
            board[tmp.rowIdx][tmp.colIdx] = 0;
        }
        return false;
    }
}
class Blank {
    int rowIdx, colIdx;
    public Blank(int rowIdx, int colIdx) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
    }
}