import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String star = "*";
        StringBuilder print = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            print.append(star + '\n');
            star += "*";
        }
        System.out.println(print);
    }
}