import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        char[][] input = new char[n][m];
        for (int i = 0; i < n; i++) {
            input[i] = br.readLine().toCharArray();
        }
        int[][] dp = new int[n][m];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (input[i][j] == '0') continue;

                int part1 = 0, part2 = (i >= 1 && j >= 1) ? dp[i - 1][j - 1] : 0, part3 = 0;
                int rowIndex = i - 1;
                while (rowIndex >= 0 && input[rowIndex][j] == '1') {
                    rowIndex--;
                    part1++;
                }

                int colIndex = j - 1;
                while (colIndex >= 0 && input[i][colIndex] == '1') {
                    colIndex--;
                    part3++;
                }

                dp[i][j] = Math.min(part1, Math.min(part2, part3)) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        System.out.println(max * max);
    }
}