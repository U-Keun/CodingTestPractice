import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] cows = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();

        Arrays.sort(cows, Comparator.comparing(a -> a[0]));

        int current = 0;
        for (int i = 0; i < N; i++) {
            if (current < cows[i][0]) {
                current = cows[i][0] + cows[i][1];
            } else {
                current += cows[i][1];
            }
        }

        System.out.println(current);
    }
}