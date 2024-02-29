import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static double[][] W, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new double[N][1 << N];
        StringTokenizer st;
        int[][] positions = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            positions[i][0] = Integer.parseInt(st.nextToken());
            positions[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();
        W = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (i == j) {
                    W[i][j] = 3000;
                    continue;
                }
                int xDiff = positions[i][0] - positions[j][0], yDiff = positions[i][1] - positions[j][1];
                double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                W[i][j] = distance;
                W[j][i] = distance;
            }
        }
        dp = new double[N][1 << N];
        System.out.println(getMin(0, 1));
    }
    private static double getMin(int current, int visited) {
        if (dp[current][visited] != 0) return dp[current][visited];
        if (visited == (1 << N) - 1) {
            return dp[current][visited] = W[current][0];
        }
        double min = 3000 * N;
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != 0) continue;
            min = Math.min(min, getMin(i, visited | (1 << i)) + W[current][i]);
        }
        return dp[current][visited] = min;
    }
}