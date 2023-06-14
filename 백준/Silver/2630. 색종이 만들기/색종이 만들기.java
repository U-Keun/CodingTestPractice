import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, blue = 0, white = 0;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count(paper);
        System.out.println(white);
        System.out.println(blue);
    }
    static boolean isMonochromatic(int[][] paper) {
        return Arrays.stream(paper).allMatch(x -> Arrays.stream(x).allMatch(y -> y == 1))
                || Arrays.stream(paper).allMatch(x -> Arrays.stream(x).allMatch(y -> y == 0));
    }
    static void count(int[][] paper) {
        if (isMonochromatic(paper)) {
            if (paper[0][0] == 1) blue++;
            else white++;
            return;
        }
        int k = paper.length / 2;
        int[][] divided;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                divided = new int[k][k];
                for (int l = 0; l < k; l++) {
                    System.arraycopy(paper[i * k + l], j * k, divided[l], 0, k);
                }
                count(divided);
            }
        }
    }
}