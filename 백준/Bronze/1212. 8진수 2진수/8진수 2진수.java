import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            char[] input = br.readLine().toCharArray();
            int n = input.length;
            StringBuilder print = new StringBuilder();
            print.append(Integer.toBinaryString(input[0] - '0'));
            for (int i = 1; i < n; i++) {
                int number = input[i] - '0';
                String binaryString = Integer.toBinaryString(number);
                print.append("0".repeat(3 - binaryString.length()));
                print.append(Integer.toBinaryString(number));
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
}