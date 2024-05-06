import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        br.close();
        long answer = 0;
        for (int i = 0; i < N; i++) {
            answer += N * i + i;
        }
        System.out.println(answer);
    }
}