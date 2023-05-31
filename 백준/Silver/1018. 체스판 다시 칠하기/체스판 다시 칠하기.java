import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, min;
    static char[][] input;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        min = 64;
        input = new char[N][M];
        for (int i = 0; i < N; i++) {
            input[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j <  M - 7; j++) {
                int k = count(i, j);
                min = Math.min(k, min);
            }
        }
        System.out.println(min);
    }
    static int count(int i, int j) {
        int verW = 0, verB = 0;
        boolean switchColor = false;
        char c;
        for (int k = 0; k < 8; k++) {
            if (switchColor) switchColor = false;
            else switchColor = true;
            for (int l = 0; l < 8; l++) {
                c = input[i + k][j + l];
                if (c == 'W') {
                    if (switchColor) {
                        verB++;
                        switchColor = false;
                    } else {
                        verW++;
                        switchColor = true;
                    }
                } else {
                    if (switchColor) {
                        verW++;
                        switchColor = false;
                    } else {
                        verB++;
                        switchColor = true;
                    }
                }
            }
        }
        return Math.min(verW, verB);
    }
}