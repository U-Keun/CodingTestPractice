import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int k, n;
        int[][] apartment = new int[14][14];
        int answer;
        for (int i = 0; i < T; i++) {
            answer = 0;
            k = Integer.parseInt(br.readLine());
            n = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < n; l++) {
                    if (j == 0) {
                        if (apartment[j][l] == 0) for (int m = 1; m <= l + 1; m++) apartment[j][l] += m;
                    } else {
                        if (apartment[j][l] == 0) for (int m = 0; m <= l; m++) apartment[j][l] += apartment[j - 1][m];
                    }
                }
            }
            if (k == 1) print.append(apartment[0][n - 1]).append('\n');
            else print.append(apartment[k - 1][n -1]).append('\n');
        }
        System.out.println(print);
    }
}