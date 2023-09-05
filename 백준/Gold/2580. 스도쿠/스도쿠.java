import java.io.IOException;
import java.util.*;

public class Main {
    static StringBuilder print = new StringBuilder();
    static int M;
    static int[][] board = new int[9][9];
    static boolean[][] rowCheckSheet = new boolean[9][10],
            colCheckSheet = new boolean[9][10],
            boxCheckSheet = new boolean[9][10];
    static ArrayList<Blank> blanks = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = readInt();
                if (board[i][j] == 0) {
                    blanks.add(new Blank(i, j));
                } else {
                    rowCheckSheet[i][board[i][j]] = true;
                    colCheckSheet[j][board[i][j]] = true;
                    boxCheckSheet[i / 3 * 3 + j / 3][board[i][j]] = true;
                }
            }
        }
        M = blanks.size();
        recurrence(0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                print.append(board[i][j]).append(" ");
            }
            print.append('\n');
        }
        System.out.println(print);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
    public static boolean recurrence(int j) {
        if (j == M) return true;
        Blank tmp = blanks.get(j);
        for (int i = 1; i <= 9; i++) {
            if (rowCheckSheet[tmp.rowIdx][i]
                    || colCheckSheet[tmp.colIdx][i]
                    || boxCheckSheet[tmp.rowIdx / 3 * 3 + tmp.colIdx / 3][i]) continue;
            rowCheckSheet[tmp.rowIdx][i] = true;
            colCheckSheet[tmp.colIdx][i] = true;
            boxCheckSheet[tmp.rowIdx / 3 * 3 + tmp.colIdx / 3][i] = true;
            board[tmp.rowIdx][tmp.colIdx] = i;
            if (recurrence(j + 1)) return true;
            rowCheckSheet[tmp.rowIdx][i] = false;
            colCheckSheet[tmp.colIdx][i] = false;
            boxCheckSheet[tmp.rowIdx / 3 * 3 + tmp.colIdx / 3][i] = false;
            board[tmp.rowIdx][tmp.colIdx] = 0;
        }
        return false;
    }
}
class Blank implements Comparable<Blank> {
    int rowIdx, colIdx;
    public Blank(int rowIdx, int colIdx) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
    }
    @Override
    public int compareTo(Blank other) {

        return 0;
    }
    private int countCandidate(Blank blank) {

        return 0;
    }
}