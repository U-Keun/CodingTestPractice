import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 1;
        int law = 1;
        int i = 1;
        while (N > law) {
            answer++;
            law += i * 6;
            i++;
        }
        System.out.println(answer);
    }
}