import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int[][] paper = new int[100][100];
        int N = readInt();
        for (int i = 0; i < N; i++) {
            int a = readInt(), b = readInt();
            for (int j = a - 1; j < a + 9; j++) {
                for (int k = b - 1; k < b + 9; k++) {
                    if (paper[j][k] == 0) paper[j][k]++;
                }
            }
        }
        int area = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                area += paper[i][j];
            }
        }
        System.out.println(area);
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
}