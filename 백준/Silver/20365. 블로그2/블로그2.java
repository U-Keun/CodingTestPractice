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
            char[] array = br.readLine().toCharArray();
            int blue = 0, red = 0;
            char prev = '-';
            for (int i = 0; i < N; i++) {
                char present = array[i];
                if (present == 'B' && present != prev) {
                    blue++;
                    prev = present;
                    continue;
                }
                if (present == 'R' && present != prev) {
                    red++;
                    prev = present;
                }
            }
            bw.write(String.valueOf(Math.min(blue, red) + 1));
            bw.flush();
        }
    }
}