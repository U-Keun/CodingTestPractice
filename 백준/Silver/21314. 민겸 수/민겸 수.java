import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            char[] input = br.readLine().toCharArray();
            StringBuilder max = new StringBuilder(), min = new StringBuilder();
            int countM = 0;
            for (char c : input) {
                if (c == 'M') {
                    countM++;
                    continue;
                }
                max.append(BigInteger.valueOf(10).pow(countM).multiply(BigInteger.valueOf(5)));
                if (countM > 0) {
                    min.append(BigInteger.valueOf(10).pow(countM - 1));
                }
                min.append(5);
                countM = 0;
            }
            if (countM > 0) {
                max.append("1".repeat(countM));
                min.append(BigInteger.valueOf(10).pow(countM - 1));
            }
            bw.write(max.toString());
            bw.write("\n");
            bw.write(min.toString());
            bw.flush();
        }
    }
}