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
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < N / 2; i++) {
                print.append("1 2 ");
            }
            if (N % 2 == 1) {
                print.append("3");
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
}