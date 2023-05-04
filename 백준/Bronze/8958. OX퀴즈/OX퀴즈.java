import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int combo;
        int score;
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            score = 0;
            combo = 1;
            for (String s:input) {
                if (s.equals("O")) {
                    score += combo++;
                } else {
                    combo = 1;
                }
            }
            print.append(score + "\n");
        }
        System.out.println(print);
    }
}