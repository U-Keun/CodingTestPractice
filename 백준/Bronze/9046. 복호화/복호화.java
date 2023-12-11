import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            for (int i = 0; i < T; i++) {
                char[] chars = br.readLine().toCharArray();
                int[] alphabets = new int[26];
                for (char c : chars) {
                    if (c == ' ') continue;
                    alphabets[c - 'a']++;
                }
                int max = 0, idx = 0;
                boolean dup = false;
                for (int j = 0; j < 26; j++) {
                    if (alphabets[j] > max) {
                        max = alphabets[j];
                        idx = j;
                        dup = false;
                    } else if (alphabets[j] == max) {
                        dup = true;
                    }
                }
                if (dup) {
                    bw.write("?");
                } else {
                    bw.write(String.valueOf((char) ('a' + idx)));
                }
                bw.write("\n");
            }
            bw.flush();
        }
    }
}