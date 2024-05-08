import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] NM;
    private static int[][] room;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        NM = readIntegers(br.readLine());
        int[] phase = readIntegers(br.readLine());
        room = new int[NM[0]][NM[1]];

        for (int i = 0; i < NM[0]; i++) {
            room[i] = readIntegers(br.readLine());
        }
        br.close();

        int answer = 0;
        while (true) {
            if (room[phase[0]][phase[1]] == 0) {
                room[phase[0]][phase[1]] = 2;
                answer++;
            }
            if (isMovable(phase[0], phase[1])) {
                phase[2]--;
                if (phase[2] < 0) phase[2] += 4;
                int[] forward = getDirection(phase[2]);
                int nextRow = phase[0] + forward[0], nextCol = phase[1] + forward[1];
                if (nextRow >= 0 && nextRow < NM[0]
                    && nextCol >= 0 && nextCol < NM[1]
                    && room[nextRow][nextCol] == 0) {
                    phase[0] = nextRow;
                    phase[1] = nextCol;
                }
                continue;
            }

            int[] backward = getDirection(phase[2] < 2 ? phase[2] + 2 : phase[2] - 2);
            int nextRow = phase[0] + backward[0], nextCol = phase[1] + backward[1];
            if (nextRow < 1 || nextRow >= NM[0] - 1
                || nextCol < 1 && nextCol >= NM[1] - 1
                || room[nextRow][nextCol] == 1) {
                break;
            }
            phase[0] = nextRow;
            phase[1] = nextCol;
        }
        System.out.println(answer);
    }
    private static boolean isMovable(int row, int col) {
        int[] direction;
        for (int i = 0; i < 4; i++) {
            direction = getDirection(i);
            int nextRow = row + direction[0], nextCol = col + direction[1];
            if (nextRow >= 0 && nextRow < NM[0]
                && nextCol >= 0 && nextCol < NM[1]
                && room[nextRow][nextCol] == 0) return true;
        }
        return false;
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
    private static int[] getDirection(int digit) throws IllegalArgumentException {
        return switch (digit) {
            case 0 -> new int[]{-1, 0};
            case 1 -> new int[]{0, 1};
            case 2 -> new int[]{1, 0};
            case 3 -> new int[]{0, -1};
            default -> throw new IllegalArgumentException("No such direction.");
        };
    }
}