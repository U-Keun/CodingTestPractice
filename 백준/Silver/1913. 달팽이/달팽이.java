import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    private enum Direction {
        SOUTH(1, 0), EAST(0, 1), NORTH(-1, 0), WEST(0, -1);
        final int i, j;
        Direction(int i, int j) {
            this.i = i;
            this.j = j;
        }
        int[] getDirection() {
            return new int[]{i, j};
        }
        Direction switchDirection() {
            if (this == SOUTH) return EAST;
            if (this == EAST) return NORTH;
            if (this == NORTH) return WEST;
            return SOUTH;
        }
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            int[][] board = new int[N][N];
            int number = N * N;
            int[] coordinate = new int[2];
            int[] answer = new int[2];
            Direction now = Direction.SOUTH;
            while (number > 0) {
                if (number == M) {
                    answer[0] = coordinate[0] + 1;
                    answer[1] = coordinate[1] + 1;
                }
                board[coordinate[0]][coordinate[1]] = number;

                int x = coordinate[0] + now.getDirection()[0];
                int y = coordinate[1] + now.getDirection()[1];
                if (x > N - 1 || x < 0 || y > N - 1 || y < 0 || board[x][y] != 0) {
                    now = now.switchDirection();
                }
                int[] direction = now.getDirection();
                coordinate[0] = coordinate[0] + direction[0];
                coordinate[1] = coordinate[1] + direction[1];
                number--;
            }
            StringBuilder print = new StringBuilder();
            for (int[] row : board) {
                print.append(Arrays.stream(row)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")));
                print.append('\n');
            }
            print.append(Arrays.stream(answer)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" ")));
            bw.write(print.toString());
            bw.flush();
        }
    }
}