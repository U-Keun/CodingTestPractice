import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            answer += Integer.parseInt(input.charAt(i) + "");
        }
        System.out.println(answer);
    }
}