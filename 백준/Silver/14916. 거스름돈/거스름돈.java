import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            if (N == 1 || N == 3) {
                bw.write(String.valueOf(-1));
                bw.flush();
                return;
            }
            int coins = 0;
            if (N % 2 == 0) {
                coins += N / 10 * 2;
                coins += N % 10 / 2;
            } else {
                coins += (N / 5 % 2 == 0) ? N / 5 - 1:N / 5;
                coins += (N - coins * 5) / 2;
            }
            bw.write(String.valueOf(coins));
            bw.flush();
        }
    }
}