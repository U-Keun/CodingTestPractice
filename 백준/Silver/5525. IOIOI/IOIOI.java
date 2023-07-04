import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, count = 0;
    static String S, P;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        P = "";
        M = Integer.parseInt(br.readLine());
        S = br.readLine();
        for (int i = 0; i < N; i++) {
            P += "IO";
        }
        P += "I";
        for (int i = 0; i < M - 2 * N; i++) {
            if (S.substring(i, i + 2 * N + 1).equals(P)) count++;
        }
        System.out.println(count);
    }
}