import java.io.*;
import java.util.*;

public class Main {
    private static class Person {
        int rowIdx, colIdx, length;
        boolean canSmash;

        public Person(int rowIdx, int colIdx, int length, boolean canSmash) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.length = length;
            this.canSmash = canSmash;
        }
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]);
            char[][] map = new char[N][M];
            for (int i = 0; i < N; i++) {
                String row = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = row.charAt(j);
                }
            }
            boolean[][][] visited = new boolean[N][M][2];
            Queue<Person> queue = new LinkedList<>();
            visited[0][0][0] = true;
            queue.offer(new Person(0, 0, 1, true));
            int[][] diff = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            while (!queue.isEmpty()) {
                Person tmp = queue.poll();
                if (tmp.rowIdx == N - 1 && tmp.colIdx == M - 1) {
                    bw.write(String.valueOf(tmp.length));
                    break;
                }
                for (int[] i:diff) {
                    int newRowIdx = tmp.rowIdx + i[0], newColIdx = tmp.colIdx + i[1];
                    if (newRowIdx >= 0 && newRowIdx < N
                        && newColIdx >= 0 && newColIdx < M) {
                        if (tmp.canSmash) {
                            if (!visited[newRowIdx][newColIdx][0]) {
                                visited[newRowIdx][newColIdx][0] = true;
                                if (map[newRowIdx][newColIdx] == '1') queue.offer(new Person(newRowIdx, newColIdx, tmp.length + 1, false));
                                else queue.offer(new Person(newRowIdx, newColIdx, tmp.length + 1, true));
                            }
                        } else {
                            if (!visited[newRowIdx][newColIdx][1] && map[newRowIdx][newColIdx] == '0') {
                                visited[newRowIdx][newColIdx][1] = true;
                                queue.offer(new Person(newRowIdx, newColIdx, tmp.length + 1, false));
                            }
                        }
                    }
                }
            }
            if (!visited[N - 1][M - 1][0] && !visited[N - 1][M - 1][1]) bw.write(String.valueOf(-1));
            bw.flush();
        }
    }
}