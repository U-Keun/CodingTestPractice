import java.io.IOException;

public class Main {
    private static int R, C, cleaner;
    private static int[][] room;
    public static void main(String[] args) throws IOException {
        R = readInt();
        C = readInt();
        room = new int[R][C];
        int T = readInt();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                room[i][j] = readInt();
                if (room[i][j] == -1 && cleaner == 0) {
                    cleaner = i;
                }
            }
        }
        for (int i = 0; i < T; i++) {
            diffuse();
            cleanerOperate();
        }
        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] == 0 || room[i][j] == -1) continue;
                answer += room[i][j];
            }
        }
        System.out.println(answer);
    }
    private static void diffuse() {
        int[][] update = new int[R][C];
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] == 0) continue;
                if (room[i][j] == -1) {
                    update[i][j] = -1;
                    continue;
                }
                int dust = room[i][j] / 5;
                for (int[] direction : directions) {
                    int adjRow = i + direction[0], adjCol = j + direction[1];
                    if (adjRow >= 0 && adjRow < R
                    && adjCol >= 0 && adjCol < C
                    && room[adjRow][adjCol] != -1) {
                        update[adjRow][adjCol] += dust;
                        room[i][j] -= dust;
                    }
                }
                update[i][j] += room[i][j];
            }
        }
        room = update;
    }
    private static void cleanerOperate() {
        for (int i = cleaner - 1; i > 0; i--) {
            room[i][0] = room[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            room[0][i] = room[0][i + 1];
        }
        for (int i = 0; i < cleaner; i++) {
            room[i][C - 1] = room[i + 1][C - 1];
        }
        for (int i = C - 1; i > 0; i--) {
            if (i == 1) {
                room[cleaner][i] = 0;
                continue;
            }
            room[cleaner][i] = room[cleaner][i - 1];
        }
        for (int i = cleaner + 2; i < R - 1; i++) {
            room[i][0] = room[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            room[R - 1][i] = room[R - 1][i + 1];
        }
        for (int i = R - 1; i > cleaner + 1; i--) {
            room[i][C - 1] = room[i - 1][C - 1];
        }
        for (int i = C - 1; i > 0; i--) {
            if (i == 1) {
                room[cleaner + 1][i] = 0;
                continue;
            }
            room[cleaner + 1][i] = room[cleaner + 1][i - 1];
        }
    }

    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
}