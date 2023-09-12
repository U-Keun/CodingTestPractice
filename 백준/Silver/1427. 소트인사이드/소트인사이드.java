import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        char[] input = br.readLine().toCharArray();
        br.close();
        Arrays.sort(input);
        for (int i = input.length - 1; i > -1; i--) {
            bw.write(input[i] + "");
        }
        bw.flush();
        bw.close();
    }
}