import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int[][] matrix = new int[9][9];
        int max = 0, row = 0, col = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = readInt();
                if (max < matrix[i][j]) {
                    row = i;
                    col = j;
                    max = matrix[i][j];
                }
            }
        }
        StringBuilder print = new StringBuilder();
        print.append(max).append('\n');
        print.append((row + 1) + " " + (col + 1));
        System.out.println(print);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag)
            c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (flag)
            return -val;
        return val;
    }
}