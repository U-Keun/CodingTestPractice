import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine()), M, N, x, y;
            StringTokenizer st;
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < T; i++) {
                st = new StringTokenizer(br.readLine());
                M = Integer.parseInt(st.nextToken());
                N = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                int s = x, t = x % N == 0? N : x % N, count = x, lcm = getLCM(M, N);
                while (count <= lcm) {
                    if (t == y) break;
                    t += M;
                    t = (t % N == 0)? N : t % N;
                    count += M;
                }
                if (count <= lcm) print.append(count);
                else print.append(-1);
                print.append('\n');
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
    private static int getLCM(int a, int b) {
        return a * b / getGCD(a, b);
    }
    private static int getGCD(int a, int b) {
        int x = Math.max(a, b), y = Math.min(a, b);
        while (x % y != 0) {
            int tmp = x % y;
            x = y;
            y = tmp;
        }
        return y;
    }
}