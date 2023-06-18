import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, r, c, k = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        find(n, r, c);
    }
    static void find(int n, int row, int col) {
        if (n == 0) {
            System.out.println(k);
            return;
        }
        if (row >= n / 2) {
            r -= n / 2;
            k += n * n / 2;
        }
        if (col >= n / 2) {
            c -= n / 2;
            k += n * n / 4;
        }
        find(n / 2, r, c);
    }
}