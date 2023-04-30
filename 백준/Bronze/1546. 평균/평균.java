import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        double answer = 0;
        int score, max = 0;
        for (int i = 0; i < n; i++) {
            score = Integer.parseInt(st.nextToken());
            if (score > max) max = score;
            answer += score;
        }
        System.out.println(answer / max * 100 / n);
    }
}