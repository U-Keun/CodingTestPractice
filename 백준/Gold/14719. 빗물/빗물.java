import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int H, W, count, answer = 0;
    static int[][] section;
    static boolean countOn;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        section = new int[H][W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int k = Integer.parseInt(st.nextToken());
            int idx = H - 1;
            while (k > 0) {
                section[idx][i] = 1;
                idx--;
                k--;
            }
        }
        for (int i = 0; i < H; i++) {
            countOn = false;
            count = 0;
            for (int j = 0; j < W; j++) {
                if (countOn) {
                    if (section[i][j] == 1) {
                        answer += count;
                        count = 0;
                    } else count++;
                } else if (section[i][j] == 1) countOn = true;
            }
        }
        System.out.println(answer);
    }
}