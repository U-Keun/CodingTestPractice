import java.io.*;
import java.util.HashSet;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        br.close();
        int n = input.length();
        HashSet<String> substrings = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                substrings.add(input.substring(j, j + i + 1));
            }
        }
        bw.write(String.valueOf(substrings.size()));
        bw.flush();
        bw.close();
    }
}