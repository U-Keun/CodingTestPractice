import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, p1 = 0, p2, ansIdx1, ansIdx2;
    static long value = Long.MAX_VALUE;
    static long[] solution;

    public static void main(String[] args) throws IOException {
        setVariables();
        while (p1 < p2) {
            long tmp = solution[p1] + solution[p2];
            if (Math.abs(tmp) < value) {
                ansIdx1 = p1;
                ansIdx2 = p2;
                value = Math.abs(tmp);
            }
            if (tmp > 0) p2--;
            else if (tmp < 0) p1++;
            else break;
        }
        System.out.println(solution[ansIdx1] + " " + solution[ansIdx2]);
    }
    static void setVariables() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        solution = new long[N];
        for (int i = 0; i < N; i++) {
            solution[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(solution);
        p2 = N - 1;
    }
}