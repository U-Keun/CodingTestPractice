import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, answer = 0;
    static int[][] grid;
    static Queue<Character> direction = new LinkedList<>();
    static int[] kernel;

    public static void main(String[] args) throws IOException {
        setVariables();
        for (int i = 1; i <= 2 * N - 1; i++) {
            int k = 0;
            if (i == 2 * N - 1) k++;
            for (int j = 0; j < Math.ceil((double) i / 2) - k; j++) {
                moveSand(direction.peek());
            }
            direction.add(direction.poll());
        }
        System.out.println(answer);
    }
    static void setVariables() throws IOException {
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        direction.add('W');
        direction.add('S');
        direction.add('E');
        direction.add('N');
        kernel = new int[]{N / 2, N / 2};
    }
    static void moveSand(char direction) {
        int[] percent = new int[]{5, 10, 10, 2, 7, 7, 2, 1, 1};
        switch (direction) {
            case 'W':
                kernel[1]--;
                int[] colIdx1 = {-2, -1, -1, 0, 0, 0, 0, 1, 1},
                        rowIdx1 = {0, 1, -1, 2, 1, -1, -2, 1, -1};
                spreadSand(percent, colIdx1, rowIdx1);
                if (kernel[1] == 0) answer += grid[kernel[0]][kernel[1]];
                else grid[kernel[0]][kernel[1] - 1] += grid[kernel[0]][kernel[1]];
                grid[kernel[0]][kernel[1]] = 0;
                break;
            case 'S':
                kernel[0]++;
                int[] colIdx2 = {0, -1, 1, -2, -1, 1, 2, -1, 1},
                        rowIdx2 = {2, 1, 1, 0, 0, 0, 0, -1, -1};
                spreadSand(percent, colIdx2, rowIdx2);
                if (kernel[0] == N - 1) answer += grid[kernel[0]][kernel[1]];
                else grid[kernel[0] + 1][kernel[1]] += grid[kernel[0]][kernel[1]];
                grid[kernel[0]][kernel[1]] = 0;
                break;
            case 'E':
                kernel[1]++;
                int[] colIdx3 = {2, 1, 1, 0, 0, 0, 0, -1, -1},
                        rowIdx3 = {0, 1, -1, 2, 1, -1, -2, 1, -1};
                spreadSand(percent, colIdx3, rowIdx3);
                if (kernel[1] == N - 1) answer += grid[kernel[0]][kernel[1]];
                else grid[kernel[0]][kernel[1] + 1] += grid[kernel[0]][kernel[1]];
                grid[kernel[0]][kernel[1]] = 0;
                break;
            case 'N':
                kernel[0]--;
                int[] colIdx4 = {0, -1, 1, -2, -1, 1, 2, -1, 1},
                        rowIdx4 = {-2, -1, -1, 0, 0, 0, 0, 1, 1};
                spreadSand(percent, colIdx4, rowIdx4);
                if (kernel[0] == 0) answer += grid[kernel[0]][kernel[1]];
                else grid[kernel[0] - 1][kernel[1]] += grid[kernel[0]][kernel[1]];
                grid[kernel[0]][kernel[1]] = 0;
                break;
        }
    }
    static void spreadSand(int[] percent, int[] colIdx, int[] rowIdx) {
        int spreadedSand;
        int record = 0;
        for (int i = 0; i < 9; i++) {
            int newRow = kernel[0] + rowIdx[i], newCol = kernel[1] + colIdx[i];
            spreadedSand = grid[kernel[0]][kernel[1]] * percent[i] / 100;
            record += spreadedSand;
            if (newRow >= 0 && newRow < N
                    && newCol >= 0 && newCol < N) {
                grid[newRow][newCol] += spreadedSand;
            } else {
                answer += spreadedSand;
            }
        }
        grid[kernel[0]][kernel[1]] -= record;
    }
}