import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static long[][] points;
    static double answer = 0;

    public static void main(String[] args) throws IOException {
        setVariables();
        for (int i = 1; i < N - 1; i++) {
            answer += triangle(i);
        }
        System.out.printf("%.1f", Math.abs(answer) * 0.5);
    }
    static void setVariables() throws IOException {
        N = Integer.parseInt(br.readLine());
        points = new long[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Long.parseLong(st.nextToken());
            points[i][1] = Long.parseLong(st.nextToken());
        }
    }
    static long triangle(int idx) {
        long area = 0;
        area += determinant(points[0][0], points[0][1], points[idx][0], points[idx][1]);
        area += determinant(points[idx][0], points[idx][1], points[idx + 1][0], points[idx + 1][1]);
        area += determinant(points[idx + 1][0], points[idx + 1][1], points[0][0], points[0][1]);
        return area;
    }
    static long determinant(long x1, long y1, long x2, long y2) {
        return x1 * y2 - x2 * y1;
    }
}