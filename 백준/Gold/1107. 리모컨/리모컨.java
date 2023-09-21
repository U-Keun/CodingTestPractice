import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] numberButtons = new boolean[10];
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());
            Arrays.fill(numberButtons, true);
            if (M > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 0; i < M; i++) numberButtons[Integer.parseInt(st.nextToken())] = false;
            }
            if (M == 10) {
                bw.write(String.valueOf(Math.abs(N - 100)));
            } else {
                int upper = N, lower = N, answer = 0;
                while (!shortcut(upper) && !shortcut(lower)) {
                    upper++;
                    if (lower > 0) lower--;
                    answer++;
                }
                int buttons = Integer.MAX_VALUE;
                if (shortcut(upper)) buttons = Math.min(String.valueOf(upper).length(), buttons);
                if (shortcut(lower)) buttons = Math.min(String.valueOf(lower).length(), buttons);
                answer = Math.min(answer + buttons, Math.abs(N - 100));
                bw.write(String.valueOf(answer));
            }
            bw.flush();
        }
    }
    public static boolean shortcut(int N) {
        char[] digits = String.valueOf(N).toCharArray();
        for (char c:digits) if (!numberButtons[c - '0']) return false;
        return true;
    }
}