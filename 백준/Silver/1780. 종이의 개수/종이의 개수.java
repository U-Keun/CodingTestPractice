import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[][] matrix;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            matrix = new int[N][N];
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) matrix[i][j] = Integer.parseInt(st.nextToken());
            }
            int[] answer = recurrence(N, 0, 0);
            StringBuilder sb = new StringBuilder();
            for (int i:answer) sb.append(i).append('\n');
            bw.write(sb.toString());
            bw.flush();
        }
    }
    private static int[] recurrence(int N, int rowIdx, int colIdx) {
        int[] answer = new int[3];
        if (N == 1) {
            switch (matrix[rowIdx][colIdx]) {
                case -1: answer[0]++; break;
                case 0: answer[1]++; break;
                case 1: answer[2]++; break;
            }
            return answer;
        }
        for (int i = 0; i < 9; i++) {
            int[] tmp = recurrence(N / 3, rowIdx + (N / 3) * (i / 3), colIdx + (N / 3) * ( i % 3));
            for (int j = 0; j < 3; j++) {
                answer[j] += tmp[j];
            }
        }
        if (answer[0] == 0 && answer[1] == 0) return new int[]{0, 0, 1};
        if (answer[0] == 0 && answer[2] == 0) return new int[]{0, 1, 0};
        if (answer[1] == 0 && answer[2] == 0) return new int[]{1, 0, 0};
        return answer;
    }
}