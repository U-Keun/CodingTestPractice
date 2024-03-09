import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String answer = "";
        for (Character c : input.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                answer += Character.toUpperCase(c);
            } else answer += Character.toLowerCase(c);
        }
        System.out.println(answer);
    }
}