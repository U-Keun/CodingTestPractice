import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[][] checkPossible = new boolean[2][40001];
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(st.nextToken());
            checkPossible[1][k] = true;
            for (int j = 1; j < 15001; j++) {
                if (checkPossible[0][j]) {
                    checkPossible[1][Math.abs(j - k)] = true;
                    checkPossible[1][j + k] = true;
                }
            }
            for (int j = 0; j < 15001; j++) {
                checkPossible[0][j] = checkPossible[1][j];
            }
        }
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int j = Integer.parseInt(st.nextToken());
            if (checkPossible[1][j]) print.append("Y ");
            else print.append("N ");
        }
        System.out.println(print);
    }
}