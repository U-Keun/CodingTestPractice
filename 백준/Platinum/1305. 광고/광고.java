import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int L = Integer.parseInt(br.readLine());
            String phrases = br.readLine();
            bw.write(String.valueOf(L - record(phrases)[L - 1]));
            bw.flush();
        }
    }
    public static int[] record(String s) {
        int m = s.length();
        int[] answer = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = answer[j - 1];
            if (s.charAt(i) == s.charAt(j)) answer[i] = ++j;
        }
        return answer;
    }
}