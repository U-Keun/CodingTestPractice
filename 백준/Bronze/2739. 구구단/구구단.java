import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            print.append(N + " * " + (i + 1) + " = " + N * (i + 1) + '\n');
        }
        System.out.println(print);
    }
}