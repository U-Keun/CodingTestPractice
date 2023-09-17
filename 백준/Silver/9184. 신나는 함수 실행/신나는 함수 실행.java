import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        String input;
        long[][][] dp = new long[21][21][21];
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                for (int k = 0; k < 21; k++) {
                    if (i == 0 || j == 0 || k == 0) dp[i][j][k] = 1;
                    else if (i < j && j < k) dp[i][j][k] = dp[i][j][k - 1] + dp[i][j - 1][k - 1] - dp[i][j - 1][k];
                    else dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - 1][k] + dp[i - 1][j][k - 1] - dp[i - 1][j - 1][k - 1];
                }
            }
        }
        StringBuilder print = new StringBuilder();
        while (!(input = br.readLine()).equals("-1 -1 -1")) {
            String[] split = input.split(" ");
            int a = Integer.parseInt(split[0]), b = Integer.parseInt(split[1]), c = Integer.parseInt(split[2]);
            if (a <= 0 || b <= 0 || c <= 0) print.append(String.format("w(%d, %d, %d) = %d", a, b, c, 1)).append('\n');
            else if (a > 20 || b > 20 || c > 20) print.append(String.format("w(%d, %d, %d) = %d", a, b, c, dp[20][20][20])).append('\n');
            else print.append(String.format("w(%d, %d, %d) = %d", a, b, c, dp[a][b][c])).append('\n');
        }
        bw.write(print.toString());
        bw.flush();
        bw.close();
    }
}