import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[][] positions = new int[N][2];
        for (int i = 0; i < N; i++) {
            positions[i][0] = readInt();
            positions[i][1] = readInt();
        }
        int round = 0;
        for (int i = 0; i < N; i++) {
            round += Math.abs(positions[i][0] - positions[(i + 1) % N][0])
                    + Math.abs(positions[i][1] - positions[(i + 1) % N][1]);
        }
        System.out.println(round);
    }
    private static int readInt() throws IOException {
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