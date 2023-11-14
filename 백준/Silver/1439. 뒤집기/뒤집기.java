import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String input = br.readLine();
            int countOne = 0, countZero = 0;
            char prev = '-';
            for (char c : input.toCharArray()) {
                if (c != prev) {
                    if (c == '1') countOne++;
                    else countZero++;
                }
                prev = c;
            }
            bw.write(String.valueOf(Math.min(countOne, countZero)));
            bw.flush();
        }
    }
}