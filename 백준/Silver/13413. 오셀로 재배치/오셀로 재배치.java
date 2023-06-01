import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static int T, N, diffB, diffW;
    static String input, goal;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            diffB = 0;
            diffW = 0;
            N = Integer.parseInt(br.readLine());
            input = br.readLine();
            goal = br.readLine();
            for (int j = 0; j < N; j++) {
                if (input.charAt(j) != goal.charAt(j)) {
                    if (input.charAt(j) == 'B') diffB++;
                    else diffW++;
                }
            }
            print.append(Math.max(diffB, diffW)).append('\n');
        }
        System.out.println(print);
    }
}